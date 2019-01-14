package br.com.android.aline.iddog.activity.login

import android.annotation.SuppressLint
import android.content.SharedPreferences
import br.com.android.aline.iddog.models.tokenreceiver.EmailUser
import br.com.android.aline.iddog.utils.PreferenceHelper
import br.com.android.aline.iddog.utils.Utils

class LoginPresenter(override val view: ILoginView) : ILoginPresenter {


    lateinit var emailUser: EmailUser
    lateinit var loginModel: LoginModel

    override fun getEmailChange() {

        view.disableButton()

        view.txtEmailTextChange().skip(1).subscribe {
            if (it.isNotEmpty() && isValidEmail(it.toString())) {
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
            loginModel = LoginModel()
            loginModel.callServiceToken(emailUser)
                    .subscribe({
                        saveCacheToken(it.user?.token)
                        view.stopAnimationLoading()
                    }, {
                        view.setError()
                        view.stopAnimationLoading()
                        view.showBtn()
                    })

        } else{
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
          if(it.isEmpty()) return
           else view.goToHome()
        }
    }
}
