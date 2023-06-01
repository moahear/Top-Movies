package com.gamil.moahear.topmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamil.moahear.topmovies.model.home.ResponseMovies
import com.gamil.moahear.topmovies.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {


    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean>
        get() = _isEmpty

    //Search
    private val _movies = MutableLiveData<ResponseMovies>()
    val movies: LiveData<ResponseMovies>
        get() = _movies

    fun searchMovies(movieName: String) =
        viewModelScope.launch {
            _loading.postValue(true)
            val response = searchRepository.searchMovies(movieName)
            if (response.isSuccessful) {
                if (response.body()?.data.isNullOrEmpty()) {
                    _isEmpty.postValue(true)
                } else {
                    _movies.postValue(response.body())
                    _isEmpty.postValue(false)
                }
            }
            _loading.postValue(false)
        }


}