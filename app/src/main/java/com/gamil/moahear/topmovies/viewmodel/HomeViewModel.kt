package com.gamil.moahear.topmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamil.moahear.topmovies.model.home.ResponseGenres
import com.gamil.moahear.topmovies.model.home.ResponseMovies
import com.gamil.moahear.topmovies.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    //loading
    private var _isLoading=MutableLiveData<Boolean>()
    val isLoading:LiveData<Boolean>
        get() = _isLoading





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

    private var _genres=MutableLiveData<ResponseGenres>()
    val genres:LiveData<ResponseGenres>
        get() = _genres
    fun  getGenres()=
        viewModelScope.launch {
            val response=homeRepository.getGenres()
            if (response.isSuccessful) {
                _genres.postValue(response.body())
            }
        }

    private var _lastMovies=MutableLiveData<ResponseMovies>()
    val lastMovies:LiveData<ResponseMovies>
        get() = _lastMovies
    fun getLastMovies()=
        viewModelScope.launch {
            _isLoading.postValue(true)
            val response= homeRepository.getLastMovies()
            if (response.isSuccessful){
                _lastMovies.postValue(response.body())
            }
            _isLoading.postValue(false)
        }


}