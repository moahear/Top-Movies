package com.gamil.moahear.topmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamil.moahear.topmovies.model.detail.ResponseDatails
import com.gamil.moahear.topmovies.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailRepository: DetailRepository) :
    ViewModel() {
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
}