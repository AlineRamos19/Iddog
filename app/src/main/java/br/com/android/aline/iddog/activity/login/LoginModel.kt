package br.com.android.aline.iddog.activity.login



import br.com.android.aline.iddog.models.tokenreceiver.EmailUser
import br.com.android.aline.iddog.models.tokenreceiver.UserResponse
import br.com.android.aline.iddog.rest.RetrofitConfig
import io.reactivex.Single

class LoginModel : ILoginModel {


    override fun callServiceToken(emailUser: EmailUser): Single<UserResponse> {

        return Single.create { emitter ->
            try {
                RetrofitConfig().getApiService().postEmailSign(emailUser)
                        .subscribe (
                            {
                                emitter.onSuccess(it)
                            },{
                            emitter.onError(it)
                        }
                            )


            } catch (error: Throwable) {

            }
        }
    }


}


