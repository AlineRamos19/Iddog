package br.com.android.aline.iddog.activity.login

import android.annotation.SuppressLint
import br.com.android.aline.iddog.enum.EnumExceptions
import br.com.android.aline.iddog.models.tokenreceiver.EmailUser
import br.com.android.aline.iddog.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class LoginPresenter(override val view: ILoginView) : ILoginPresenter {

    private lateinit var emailUser: EmailUser

    override fun getEmailChange() {

        view.disableButton()

        view.txtEmailTextChange().skip(1).subscribe {
            if (it.isNotEmpty() && isValidEmail(it.toString())) {
                view.hideErrorEmail()
                view.enableButton()
                emailUser = EmailUser(it.toString())
            } else {
                view.disableButton()
                view.showErrorInvalidEmail()
            }
        }.apply { }

        view.txtEmailTextChange().connect()
    }

    override fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    @SuppressLint("CheckResult")
    override fun getTokenService() {
        view.hideBtn()
        view.startAnimationLoading()
        if (view.checkNetwork()) {
            val model = LoginModel()
            model.callServiceToken(emailUser)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        saveCacheToken(it.user?.token)
                        view.stopAnimationLoading()
                    }, {
                        if (it is HttpException) {
                            when (it.code()) {
                                EnumExceptions.INVALID_EMAIL.code ->  view.setErrorEmailDialog()
                                EnumExceptions.NOT_AUTHORIZED.code -> view.setErrorNotAuthorized()
                                EnumExceptions.NOT_AUTHORIZED.code -> view.setError()
                                EnumExceptions.INTERNAL_SERVER_ERROR.code -> view.setError()
                            }

                        }
                        view.disableButton()
                        view.stopAnimationLoading()
                        view.showBtn()
                    })

        } else {
            view.showMessageErrorInternet()
            view.stopAnimationLoading()
            view.showBtn()
        }
    }

    override fun saveCacheToken(token: String?) {
        token.let {
            view.saveSharedPreference(it!!)
        }
    }

    override fun checkNewUser() {
        Utils.getToken().let {
            if (it.isEmpty()) return
            else view.goToHome()
        }
    }
}
