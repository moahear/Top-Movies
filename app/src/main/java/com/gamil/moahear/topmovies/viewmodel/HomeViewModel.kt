package com.gamil.moahear.topmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamil.moahear.topmovies.model.home.ResponseMovies
import com.gamil.moahear.topmovies.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {
    private var _topMovies = MutableLiveData<List<ResponseMovies.Data>>()
    val topMovies: LiveData<List<ResponseMovies.Data>>
        get() = _topMovies

    fun getTopMovies(genreId: Int) =
        viewModelScope.launch {
            val response = homeRepository.getTopMovies(genreId)
            if (response.isSuccessful) {
                response.body()?.let {
                    _topMovies.postValue(it.data)
                }
            }
        }

}