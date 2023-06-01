package com.gamil.moahear.topmovies.repository

import com.gamil.moahear.topmovies.api.ApiInterface
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiInterface: ApiInterface) {
    suspend fun getTopMovies(genreId: Int) = apiInterface.getTopMovies(genreId)
    suspend fun getGenres() = apiInterface.getGenres()
    suspend fun getLastMovies()=apiInterface.getLastMovies()
}