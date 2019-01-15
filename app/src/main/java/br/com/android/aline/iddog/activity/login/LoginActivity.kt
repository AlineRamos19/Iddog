package br.com.android.aline.iddog.activity.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.content.res.AppCompatResources
import android.view.View
import android.view.inputmethod.InputMethodManager
import br.com.android.aline.iddog.R
import br.com.android.aline.iddog.activity.home.HomeDogActivity
import br.com.android.aline.iddog.utils.PreferenceHelper
import br.com.android.aline.iddog.utils.PreferenceHelper.tokenUser
import br.com.android.aline.iddog.utils.Utils
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.observables.ConnectableObservable
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity() : AppCompatActivity(), ILoginView {



    private val presenter: ILoginPresenter = LoginPresenter(this)
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
        input_email.
                setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_error_email, 0)
    }

    override fun txtEmailTextChange(): ConnectableObservable<CharSequence> {
        return txtEmailTextChange
    }

    override fun enableButton() {
        //hideKeyBoard()
        btn_login.setTextColor(resources.getColor(R.color.colorDefaultBtn))
        btn_login.isEnabled = true
        btn_login.setBackgroundResource(R.drawable.background_btn_enable)
    }

    override fun disableButton() {
        btn_login.setTextColor(resources.getColor(R.color.colorTextBtnDisable))
        btn_login.isEnabled = false
        btn_login.setBackgroundResource(R.drawable.background_btn_disable)
    }

    override fun setError() {
       createDialog(resources.getString(R.string.dialog_error))
    }

    private fun createDialog(message: String){
        AlertDialog.Builder(this)
                .setMessage(message)
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
        presenter.checkNewUser()
    }

    override fun hideKeyBoard() {
        val view = this.currentFocus
        view.let {
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it!!.windowToken, 0)
        }
    }

    override fun startAnimationLoading() {
        loading.show()
    }

    override fun stopAnimationLoading() {
        loading.hide()
    }

    override fun hideBtn() {
        btn_login.visibility = View.INVISIBLE
    }

    override fun showBtn() {
        btn_login.visibility = View.VISIBLE
    }

    override fun setErrorEmailDialog() {
       createDialog(resources.getString(R.string.invalid_email_login))
    }

    override fun setErrorNotAuthorized(){
        createDialog(getString(R.string.error_user_unauthorized))
    }


    override fun hideErrorEmail() {
        input_email.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_email_ok, 0)
    }



}


