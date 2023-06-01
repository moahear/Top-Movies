package com.gamil.moahear.topmovies.repository

import com.gamil.moahear.topmovies.api.ApiInterface
import javax.inject.Inject

class SearchRepository @Inject constructor(private val apiInterface: ApiInterface) {
    suspend fun searchMovies(movieName: String) = apiInterface.searchMovies(movieName)
}