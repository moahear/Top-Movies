package com.gamil.moahear.topmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamil.moahear.topmovies.model.register.RequestRegisterBody
import com.gamil.moahear.topmovies.model.register.ResponseRegister
import com.gamil.moahear.topmovies.repository.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerRepository: RegisterRepository) :
    ViewModel() {

    private var _registerUser = MutableLiveData<ResponseRegister>()
    val registerUser: LiveData<ResponseRegister>
        get() = _registerUser

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun sendRegisterUser(bodyRegister: RequestRegisterBody) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val responseRegister = registerRepository.registerUser(bodyRegister)
            if (responseRegister.isSuccessful) {
                _registerUser.postValue(responseRegister.body())
            }
            _isLoading.postValue(false)
        }
    }
}