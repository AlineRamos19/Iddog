package br.com.android.aline.iddog.fragment

interface IListDogPresenter {
     val view : IListDogView
     fun getDefaultFeed()
    fun  getFeedCategory(brand : String)
}