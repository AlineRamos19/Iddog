package br.com.android.aline.iddog

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {

        var context: App? = null
            private set
    }
}