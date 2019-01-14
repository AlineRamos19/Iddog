package br.com.android.aline.iddog.models.homereceiver

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Dogs {

    @SerializedName("category")
    @Expose
    var category: String? = null
    @SerializedName("list")
    @Expose
    var list: List<String>? = null

}