package br.com.android.aline.iddog.models.tokenreceiver

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserResponse {

    @SerializedName("user")
    @Expose
    var user: User? = null

}
