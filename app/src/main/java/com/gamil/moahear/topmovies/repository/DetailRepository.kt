package com.gamil.moahear.topmovies.repository

import com.gamil.moahear.topmovies.api.ApiInterface
import com.gamil.moahear.topmovies.db.FavoriteMovieEntity
import com.gamil.moahear.topmovies.db.FavoriteMoviesDao
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val apiInterface: ApiInterface,
    private val favoriteMoviesDao: FavoriteMoviesDao
) {
    //Api
    suspend fun getMovieDetails(movieId: Int) = apiInterface.getMovieDetails(movieId)

    //Database
    suspend fun insertFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity) =
        favoriteMoviesDao.insertFavoriteMovie(favoriteMovieEntity)

    suspend fun deleteFromFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity) =
        favoriteMoviesDao.deleteFromFavoriteMovie(favoriteMovieEntity)

    suspend fun existsInFavoriteMovies(movieId: Int) =
        favoriteMoviesDao.existsInFavoriteMovies(movieId)

}