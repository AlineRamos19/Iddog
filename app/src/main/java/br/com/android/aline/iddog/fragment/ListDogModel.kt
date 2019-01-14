package br.com.android.aline.iddog.fragment

import br.com.android.aline.iddog.models.homereceiver.Dogs
import br.com.android.aline.iddog.rest.RetrofitConfig
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListDogModel : IListDogModel {

    override fun getDefaultService(): Single<Dogs> {
       return Single.create { emitter ->

               RetrofitConfig().getApiService().getListDefault()
                       .subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .subscribe ({
                            emitter.onSuccess(it)
                       },{
                            emitter.onError(it)
                       })

       }
    }

    override fun getFeedCategory(brand: String): Single<Dogs> {
       return Single.create { emitter ->

           RetrofitConfig().getApiService().getListDogs(brand)
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe ({
                       emitter.onSuccess(it)
                   },{
                       emitter.onError(it)
                   })
       }
    }
}