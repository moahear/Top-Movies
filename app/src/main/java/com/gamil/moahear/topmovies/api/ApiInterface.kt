package com.gamil.moahear.topmovies.api

import com.gamil.moahear.topmovies.model.register.RequestRegisterBody
import com.gamil.moahear.topmovies.model.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("register")
    suspend fun registerUser(@Body bodyRegister: RequestRegisterBody): Response<ResponseRegister>
}