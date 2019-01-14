package br.com.android.aline.iddog.models.tokenreceiver

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("_id")
    @Expose
    var id: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("token")
    @Expose
    var token: String? = null
    @SerializedName("createdAt")
    @Expose
    var createdAt: String? = null
    @SerializedName("updatedAt")
    @Expose
    var updatedAt: String? = null
    @SerializedName("__v")
    @Expose
    var v: Int? = null

}
