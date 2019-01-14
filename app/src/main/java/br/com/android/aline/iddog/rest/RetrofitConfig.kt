package br.com.android.aline.iddog.rest

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitConfig {

    private var retrofit: Retrofit
    private val URL_BASE = "https://api-iddog.idwall.co/"

    init {

        val client = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor())
                .connectTimeout(240, TimeUnit.SECONDS)
                .readTimeout(240, TimeUnit.SECONDS)
                .writeTimeout(240, TimeUnit.SECONDS)
                .build()


        retrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    fun getApiService(): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }

}

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        val requestBuilder = chain!!.request().newBuilder()
        requestBuilder.header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJpZGRvZy1zZXJ2aWNlIiwic3ViIjoiNWMzYTgzZTM1YTk0MDE2NzJkYTVkMDIyIiwiaWF0IjoxNTQ3MzM4NzIzLCJleHAiOjE1NDg2MzQ3MjN9.T8xaFRx0Z8J0s6K078R9SH7IOlKGOdWU1OnaBPLbk9s")

        val request = requestBuilder.build()
        val response = chain.proceed(request)

        return response
    }
}






