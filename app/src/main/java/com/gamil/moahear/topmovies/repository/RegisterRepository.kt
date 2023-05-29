package com.gamil.moahear.topmovies.repository

import com.gamil.moahear.topmovies.api.ApiInterface
import com.gamil.moahear.topmovies.model.register.RequestRegisterBody
import javax.inject.Inject

class RegisterRepository @Inject constructor(private val apiInterface: ApiInterface) {
    suspend fun registerUser(bodyRegister: RequestRegisterBody) =
        apiInterface.registerUser(bodyRegister)
}