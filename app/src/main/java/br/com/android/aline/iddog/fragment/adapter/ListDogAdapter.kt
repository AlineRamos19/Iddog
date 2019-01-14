package br.com.android.aline.iddog.fragment.adapter

import android.app.AlertDialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import br.com.android.aline.iddog.R
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.dialog_show_item_clicked.view.*


class ListDogAdapter(private val listDog: List<String>?, val context: Context)
    : RecyclerView.Adapter<ListDogAdapter.DogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        return DogViewHolder(LayoutInflater.from(context).inflate(R.layout.row_dog, parent, false))
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dogs = listDog!![position]

        val options =
                RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .priority(Priority.HIGH)

        Glide.with(context).load(dogs).apply(options).into(holder.imageDog)

        holder.itemView.setOnClickListener {
            val dialog = AlertDialog.Builder(context)
            val view = LayoutInflater.from(context).inflate(R.layout.dialog_show_item_clicked, null)
            Glide.with(context).load(dogs).into(view.image_dialog)
            dialog.setView(view)
            dialog.setPositiveButton("Ok", { dialog, _ ->
                dialog.dismiss()
            }).show()
        }

    }

    override fun getItemCount(): Int {
        return listDog!!.size
    }


    class DogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageDog = itemView.findViewById<ImageView>(R.id.image_dog)

    }
}