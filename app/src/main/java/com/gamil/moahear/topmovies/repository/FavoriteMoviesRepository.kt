package com.gamil.moahear.topmovies.repository

import com.gamil.moahear.topmovies.db.FavoriteMovieEntity
import com.gamil.moahear.topmovies.db.FavoriteMoviesDao
import javax.inject.Inject

class FavoriteMoviesRepository @Inject constructor(private val favoriteMoviesDao: FavoriteMoviesDao) {
    fun getAllFavoriteMovies() = favoriteMoviesDao.getAllFavoriteMovies()
}