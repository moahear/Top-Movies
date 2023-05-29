package com.gamil.moahear.topmovies.model.register

import com.google.gson.annotations.SerializedName

data class RequestRegisterBody(
    @SerializedName("email")
    var email: String="", // abbas@oveissi.ir
    @SerializedName("name")
    var name: String="", // Abbas Ov
    @SerializedName("password")
    var password: String=""
)