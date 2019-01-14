package br.com.android.aline.iddog.activity.login


import br.com.android.aline.iddog.models.tokenreceiver.EmailUser
import br.com.android.aline.iddog.models.tokenreceiver.UserResponse
import br.com.android.aline.iddog.rest.RetrofitConfig
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LoginModel : ILoginModel {

    override fun callServiceToken(emailUser: EmailUser): Single<UserResponse> {

        return Single.create { emitter ->
            try {
                RetrofitConfig().getApiService().postEmailSign(emailUser)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    emitter.onSuccess(it)
                                }, {
                            emitter.onError(it)
                        }

                        )
            } catch (error: Throwable) {

            }
        }
    }
}


