package br.com.android.aline.iddog.fragment

import br.com.android.aline.iddog.models.homereceiver.Dogs


interface IListDogView {

    fun callServiceApi(brand : String?)
    fun showProgress()
    fun hideProgress()
    fun setError()
    fun showList(dog : Dogs)
}