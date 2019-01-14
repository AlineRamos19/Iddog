package br.com.android.aline.iddog.fragment

import br.com.android.aline.iddog.models.homereceiver.Dogs
import io.reactivex.Single


interface IListDogModel {

    fun getDefaultService() : Single<Dogs>
    fun getFeedCategory(brand : String) : Single<Dogs>
}