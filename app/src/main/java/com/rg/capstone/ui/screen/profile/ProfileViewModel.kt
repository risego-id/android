package com.rg.capstone.ui.screen.profile

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rg.capstone.network.Resource
import com.rg.capstone.network.model.UserDto
import com.rg.capstone.network.response.LogoutResponse
import com.rg.capstone.repository.RGRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: RGRepository
): ViewModel() {

    private val _userToken = MutableStateFlow<String?>("")
    val userToken: StateFlow<String?> get() = _userToken

    private val _userId = MutableStateFlow<Int?>(-1)
    val userId: StateFlow<Int?> get() = _userId

    private val _logoutState = MutableStateFlow<Resource<LogoutResponse>>(Resource.Empty)
    val logoutState: StateFlow<Resource<LogoutResponse>> = _logoutState

    private val _userInfo = MutableStateFlow<Resource<UserDto?>>(Resource.Empty)
    val userInfo: StateFlow<Resource<UserDto?>> get() = _userInfo

    init {
        viewModelScope.launch {
            repository.getUserToken().collect { token ->
                _userToken.value = token
            }
            repository.getUserId().collect { id ->
                _userId.value = id
            }
        }
    }
    fun logout(token: String) = viewModelScope.launch {
        _logoutState.value = Resource.Loading
        try {
            val responseLiveData = repository.logout(token)
            responseLiveData.observeForever { response ->
                if (response is Resource.Success<LogoutResponse>) {
                    _logoutState.value = Resource.Success(response.data)
                } else if (response is Resource.Error) {
                    _logoutState.value = Resource.Error(response.message)
                }
            }
        } catch (e: Exception) {
            _logoutState.value = Resource.Error(e.message ?: "Unknown Error Occurred")
        }
    }

    fun getUserInfo(token: String) = viewModelScope.launch {
        _userInfo.value = Resource.Loading
        try {
            val response = repository.getUser(token)
            response.observeForever { res ->
                if (res is Resource.Success<UserDto>) {
                    _userInfo.value = Resource.Success(res.data)
                } else if (res is Resource.Error) {
                    _userInfo.value = Resource.Error(res.message)
                }
            }
        } catch (e: Exception) {
            _userInfo.value = Resource.Error(e.message ?: "Unknown Error Occurred")
        }
    }
}