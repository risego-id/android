package com.rg.capstone.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rg.capstone.network.Resource
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
}