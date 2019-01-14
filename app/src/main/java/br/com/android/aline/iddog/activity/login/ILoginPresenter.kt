package br.com.android.aline.iddog.activity.login

import android.content.SharedPreferences

interface ILoginPresenter {

    val view : ILoginView
    fun isValidEmail(email : String) : Boolean
    fun getTokenService()
    fun saveCacheToken(token : String?)
    fun getEmailChange()
    fun checkNewUser()


}