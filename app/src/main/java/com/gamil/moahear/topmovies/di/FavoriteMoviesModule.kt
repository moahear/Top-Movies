package com.gamil.moahear.topmovies.di

import android.content.Context
import androidx.room.Room
import com.gamil.moahear.topmovies.db.FavoriteMoviesDao
import com.gamil.moahear.topmovies.db.FavoriteMoviesDatabase
import com.gamil.moahear.topmovies.db.FavoriteMovieEntity
import com.gamil.moahear.topmovies.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteMoviesModule {
    @Provides
    @Singleton
    fun provideFavoriteMovieDatabase(@ApplicationContext context: Context): FavoriteMoviesDatabase =
        Room.databaseBuilder(
            context,
            FavoriteMoviesDatabase::class.java,
            Constants.FAVORITE_MOVIES_DATABASE
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideDao(favoriteMoviesDatabase: FavoriteMoviesDatabase): FavoriteMoviesDao =
        favoriteMoviesDatabase.favoriteMoviesDao

    @Provides
    @Singleton
    fun provideFavoriteMovieEntity(): FavoriteMovieEntity =
        FavoriteMovieEntity()
}