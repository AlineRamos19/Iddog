package br.com.android.aline.iddog.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.android.aline.iddog.R
import br.com.android.aline.iddog.enum.EnumBrand
import br.com.android.aline.iddog.fragment.adapter.ListDogAdapter
import br.com.android.aline.iddog.models.homereceiver.Dogs
import kotlinx.android.synthetic.main.fragment_list_dog.*

class ListDogFragment : Fragment(), IListDogView {

    private val presenter = ListDogPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_list_dog, container, false)
        val brand = arguments?.getString("BRAND")

        callServiceApi(brand)

        return rootView
    }

    override fun callServiceApi(brand: String?) {
        if (brand != null) {
            if (brand == EnumBrand.HUSKY.brand) presenter.getDefaultFeed()
            else presenter.getFeedCategory(brand)
        }
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        if(progress == null) return
        progress.visibility = View.INVISIBLE
    }

    override fun setError() {
        AlertDialog.Builder(activity!!)
                .setMessage(getString(R.string.dialog_error))
                .setCancelable(false)
                .setPositiveButton("ok") { dialog, _ ->
                    dialog.dismiss()
                }.show()
    }

    override fun showList(dog: Dogs) {
        if(recycler_dog == null) return

        recycler_dog.layoutManager = GridLayoutManager(activity!!, 2)
        recycler_dog.adapter = ListDogAdapter(dog.list, context!!)
    }

    companion object {
        fun newInstance(brand: String): ListDogFragment {
            val args = Bundle()
            args.putString("BRAND", brand)
            val frag = ListDogFragment()
            frag.arguments = args
            return frag
        }
    }
}