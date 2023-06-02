package com.gamil.moahear.topmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamil.moahear.topmovies.db.FavoriteMovieEntity
import com.gamil.moahear.topmovies.model.detail.ResponseDatails
import com.gamil.moahear.topmovies.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailRepository: DetailRepository) :
    ViewModel() {
    //Api
    private var _movieDetails = MutableLiveData<ResponseDatails>()
    val movieDetails: LiveData<ResponseDatails>
        get() = _movieDetails

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun getMovieDetails(movieId: Int) =
        viewModelScope.launch {
            _loading.postValue(true)
            val response = detailRepository.getMovieDetails(movieId)
            if (response.isSuccessful) {
                _movieDetails.postValue(response.body())
            }
            _loading.postValue(false)
        }

    //Database
    private var _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean>
        get() = _isFavorite

    suspend fun existsInFavoriteMovies(movieId: Int) =
        withContext(viewModelScope.coroutineContext) {
            detailRepository.existsInFavoriteMovies(movieId)
        }

    fun toggleFavorite(movieId: Int, movieEntity: FavoriteMovieEntity) =
        viewModelScope.launch {
            val isExist = detailRepository.existsInFavoriteMovies(movieId)
            if (isExist) {
                _isFavorite.postValue(false)
                detailRepository.deleteFromFavoriteMovie(movieEntity)
            } else {
                _isFavorite.postValue(true)
                detailRepository.insertFavoriteMovie(movieEntity)
            }
        }
}