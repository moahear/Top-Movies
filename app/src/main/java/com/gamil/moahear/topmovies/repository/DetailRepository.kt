package com.gamil.moahear.topmovies.repository

import com.gamil.moahear.topmovies.api.ApiInterface
import javax.inject.Inject

class DetailRepository @Inject constructor(private val apiInterface: ApiInterface) {
    suspend fun getMovieDetails(movieId: Int) = apiInterface.getMovieDetails(movieId)
}