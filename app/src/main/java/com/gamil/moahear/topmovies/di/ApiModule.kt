package com.gamil.moahear.topmovies.di

import com.gamil.moahear.topmovies.api.ApiInterface
import com.gamil.moahear.topmovies.di.qualifier.BodyInterceptor
import com.gamil.moahear.topmovies.di.qualifier.HeaderInterceptor
import com.gamil.moahear.topmovies.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideBaseUrl(): String {
        return Constants.BASE_URL
    }

    @Provides
    @Singleton
    fun provideConnectionTime(): Long = Constants.CONNECTION_TIME

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    @BodyInterceptor
    fun provideBodyHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    @HeaderInterceptor
    fun provideHeaderInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.HEADERS
    }


    @Provides
    @Singleton
    fun provideClient(
        @BodyInterceptor bodyInterceptor: HttpLoggingInterceptor,
        time: Long
    ): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(time, TimeUnit.SECONDS)
            .writeTimeout(time, TimeUnit.SECONDS)
            .connectTimeout(time, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(bodyInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, gson: Gson, client: OkHttpClient): ApiInterface =
        Retrofit.Builder().client(client).baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiInterface::class.java)
}