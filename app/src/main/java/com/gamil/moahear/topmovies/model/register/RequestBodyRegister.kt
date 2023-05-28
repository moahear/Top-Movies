package com.gamil.moahear.topmovies.model.register

import com.google.gson.annotations.SerializedName

data class RequestBodyRegister(
    @SerializedName("email")
    val email: String?, // abbas@oveissi.ir
    @SerializedName("name")
    val name: String?, // Abbas Ov
    @SerializedName("password")
    val password: String?
)