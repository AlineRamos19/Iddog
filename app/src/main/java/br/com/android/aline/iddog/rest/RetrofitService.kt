package br.com.android.aline.iddog.rest

import br.com.android.aline.iddog.models.homereceiver.Dogs
import br.com.android.aline.iddog.models.tokenreceiver.EmailUser
import br.com.android.aline.iddog.models.tokenreceiver.UserResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {

    @POST("signup")
    fun postEmailSign(@Body body: EmailUser) : Single<UserResponse>

    @GET("/feed")
    fun getListDogs(@Query("category") category : String) : Single<Dogs>

    @GET("/feed")
    fun getListDefault() : Single<Dogs>

}