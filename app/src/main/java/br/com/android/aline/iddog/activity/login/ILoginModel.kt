package br.com.android.aline.iddog.activity.login

import br.com.android.aline.iddog.models.tokenreceiver.EmailUser
import br.com.android.aline.iddog.models.tokenreceiver.UserResponse
import io.reactivex.Single

interface ILoginModel  {

     fun callServiceToken(emailUser: EmailUser) : Single<UserResponse>
}