package com.gamil.moahear.topmovies.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity)

    @Delete
    suspend fun deleteFromFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity)

    @Query("SELECT * FROM FAVORITE_MOVIES_TABLE")
    fun getAllFavoriteMovies(): MutableList<FavoriteMovieEntity>

    @Query("SELECT EXISTS (SELECT 1 FROM FAVORITE_MOVIES_TABLE WHERE id = :movieId)")
    suspend fun existsInFavoriteMovies(movieId: Int): Boolean
}