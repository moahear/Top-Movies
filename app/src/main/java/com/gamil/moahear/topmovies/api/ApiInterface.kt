package com.gamil.moahear.topmovies.api

import com.gamil.moahear.topmovies.model.detail.ResponseDatails
import com.gamil.moahear.topmovies.model.home.ResponseGenres
import com.gamil.moahear.topmovies.model.home.ResponseMovies
import com.gamil.moahear.topmovies.model.register.RequestRegisterBody
import com.gamil.moahear.topmovies.model.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @POST("register")
    suspend fun registerUser(@Body bodyRegister: RequestRegisterBody): Response<ResponseRegister>

    @GET("genres/{genre_id}/movies")
    suspend fun getTopMovies(@Path(value = "genre_id") genreId: Int): Response<ResponseMovies>

    @GET("genres")
    suspend fun getGenres(): Response<ResponseGenres>

    @GET("movies")
    suspend fun getLastMovies(): Response<ResponseMovies>

    @GET("movies")
    suspend fun searchMovies(@Query(value = "q") movieName: String): Response<ResponseMovies>

    @GET("movies/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): Response<ResponseDatails>

}