package com.gamil.moahear.topmovies.di

import com.gamil.moahear.topmovies.model.register.RequestRegisterBody
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RegisterModule {
    @Provides
    @Singleton
    fun provideRequestRegisterBody():RequestRegisterBody =RequestRegisterBody()
}