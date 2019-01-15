package br.com.android.aline.iddog.activity.login


import io.reactivex.observables.ConnectableObservable

interface ILoginView {

    fun checkNetwork() : Boolean
    fun showMessageErrorInternet()
    fun showErrorInvalidEmail()
    fun enableButton()
    fun disableButton()
    fun txtEmailTextChange() : ConnectableObservable<CharSequence>
    fun setError()
    fun saveSharedPreference(token : String)
    fun goToHome()
    fun checkIsNewUser()
    fun hideKeyBoard()
    fun startAnimationLoading()
    fun stopAnimationLoading()
    fun hideBtn()
    fun showBtn()
    fun setErrorEmailDialog()
    fun setErrorNotAuthorized()
    fun hideErrorEmail()

}