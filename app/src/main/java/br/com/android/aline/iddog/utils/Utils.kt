package br.com.android.aline.iddog.utils

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.preference.PreferenceManager

class Utils {

    companion object {

        fun checkAvalaibleNetwork(context: Context): Boolean {
            val connectivityManager : ConnectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
             val networkInfo  = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }
}

object PreferenceHelper {

    var TOKEN_USER = "token"
    var DEFAULT_VALUE_SHARED = ""

    fun defaultPreference(context: Context): SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context)

    fun customPreference(context: Context, name: String) =
            context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val edit = edit()
        operation(edit)
        edit.apply()
    }


    var SharedPreferences.tokenUser
        get() = getString(TOKEN_USER, DEFAULT_VALUE_SHARED)
        set(value) {
            edit { it.putString(TOKEN_USER, value) }
        }

    var SharedPreferences.clear
        get() = {}
        set(_) {
            edit {
                it.remove(TOKEN_USER)
            }
        }


}