package com.gamil.moahear.topmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamil.moahear.topmovies.db.FavoriteMovieEntity
import com.gamil.moahear.topmovies.repository.FavoriteMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(private val favoriteMoviesRepository: FavoriteMoviesRepository) :
    ViewModel() {
    private var _favoriteMovies = MutableLiveData<List<FavoriteMovieEntity>>()
    val favoriteMovies: LiveData<List<FavoriteMovieEntity>>
        get() = _favoriteMovies

    private val _empty = MutableLiveData<Boolean>()
    val empty: LiveData<Boolean>
        get() = _empty

    fun getAllFavoriteMovies() =
        viewModelScope.launch {
            val list = favoriteMoviesRepository.getAllFavoriteMovies()
            if (list.isNotEmpty()) {
                _empty.postValue(false)
                _favoriteMovies.postValue(list)
            } else {
                _empty.postValue(true)
            }
        }
}