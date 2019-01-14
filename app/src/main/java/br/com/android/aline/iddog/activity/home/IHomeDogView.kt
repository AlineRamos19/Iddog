package br.com.android.aline.iddog.activity.home


interface IHomeDogView {

    fun alertDeleteLogin()
    fun deleteTokenUser()
    fun returnLogin()
    fun configToolbar()
    fun openFragment(brand : String)
    fun openDefaultFragment()
}