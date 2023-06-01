package com.gamil.moahear.topmovies.db

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [FavoriteMovieEntity::class], version = 1, exportSchema = false)
abstract class FavoriteMoviesDatabase:RoomDatabase() {
    abstract val favoriteMoviesDao:FavoriteMoviesDao
}