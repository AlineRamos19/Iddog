package br.com.android.aline.iddog.activity.login

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import br.com.android.aline.iddog.R
import br.com.android.aline.iddog.activity.home.HomeDogActivity
import br.com.android.aline.iddog.utils.PreferenceHelper
import br.com.android.aline.iddog.utils.PreferenceHelper.tokenUser
import br.com.android.aline.iddog.utils.Utils
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.observables.ConnectableObservable
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), ILoginView {

    private val presenter: LoginPresenter = LoginPresenter(this)
    private lateinit var txtEmailTextChange: ConnectableObservable<CharSequence>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtEmailTextChange = input_email.textChanges().publish()
        presenter.getEmailChange()
        checkIsNewUser()
        btn_login.setOnClickListener { presenter.getTokenService() }

    }

    override fun checkNetwork(): Boolean {
        return Utils.checkAvalaibleNetwork(this)
    }

    override fun showMessageErrorInternet() {
        Snackbar.make(layout_login, R.string.warning_error_internet, Snackbar.LENGTH_LONG).show()
    }

    override fun showErrorInvalidEmail() {
        input_email.error = resources.getString(R.string.set_error_valid_email)
    }

    override fun txtEmailTextChange(): ConnectableObservable<CharSequence> {
        return txtEmailTextChange
    }

    override fun enableButton() {
        btn_login.isEnabled = true
        btn_login.setBackgroundResource(R.drawable.ic_enter_enable)
    }

    override fun disableButton() {
        btn_login.isEnabled = false
        btn_login.setBackgroundResource(R.drawable.ic_enter_disable)
    }

    override fun setError() {
        AlertDialog.Builder(this)
                .setMessage(getString(R.string.dialog_error))
                .setCancelable(false)
                .setPositiveButton("ok") { dialog, _ ->
                    dialog.dismiss()
                }.show()
    }

    override fun saveSharedPreference(token: String) {
        val prefs = PreferenceHelper.customPreference(applicationContext, PreferenceHelper.TOKEN_USER)
        prefs.tokenUser = token
        goToHome()
    }

    override fun goToHome() {
        startActivity(Intent(this, HomeDogActivity::class.java))
    }

    override fun checkIsNewUser() {
        val prefs = PreferenceHelper.customPreference(applicationContext, PreferenceHelper.TOKEN_USER)
        prefs.contains(PreferenceHelper.TOKEN_USER).let {
            if(it){
                val value = prefs.getString(PreferenceHelper.TOKEN_USER, PreferenceHelper.DEFAULT_VALUE_SHARED)
                if (value != "") goToHome()
            } else return
        }
    }

}


