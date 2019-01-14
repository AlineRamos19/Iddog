package br.com.android.aline.iddog.fragment

class ListDogPresenter(override val view: IListDogView) : IListDogPresenter {
    val model = ListDogModel()

    override fun getDefaultFeed() {
        model.getDefaultService()
                .doFinally { view.hideProgress() }
                .subscribe({
                    view.showList(it)
                }, {
                    view.setError()
                })
    }
    override fun getFeedCategory(brand: String) {
        model.getFeedCategory(brand)
                .doFinally { view.hideProgress() }
                .subscribe({
                    view.showList(it)
                }, {
                    view.setError()
                })
    }

}