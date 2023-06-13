package com.rg.capstone.ui.screen.login

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rg.capstone.domain.model.User
import com.rg.capstone.network.Resource
import com.rg.capstone.network.response.LoginResponse
import com.rg.capstone.repository.RGRepository
import com.rg.capstone.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: RGRepository,
): ViewModel() {
    private val _emailText = mutableStateOf("")
    val emailText: State<String> get() = _emailText

    private val _passwordText = mutableStateOf("")
    val passwordText: State<String> get() = _passwordText

    private val _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> get() = _showPassword

    private val _emailError = mutableStateOf("")
    val emailError: State<String> get() = _emailError

    private val _passwordError = mutableStateOf("")
    val passwordError: State<String> get() = _passwordError

    fun setEmailText(email: String) {
        _emailText.value = email
    }

    fun setPasswordText(password: String) {
        _passwordText.value = password
    }

    fun setShowPassword(showPassword: Boolean) {
        _showPassword.value = showPassword
    }

    private val _loginState = MutableStateFlow<Resource<LoginResponse>>(Resource.Empty)
    val loginState: StateFlow<Resource<LoginResponse>> = _loginState

    fun userLogin(email: String, password: String) = viewModelScope.launch {
        _loginState.value = Resource.Loading
        try {
            val responseLiveData = repository.login(email, password)
            responseLiveData.observeForever { response ->
                if (response is Resource.Success<LoginResponse>) {
                    // Login successful
                    _loginState.value = Resource.Success(response.data)
                } else if (response is Resource.Error) {
                    // Login failed
                    _loginState.value = Resource.Error(response.message)
                }
            }
        } catch (e: Exception) {
            // Login failed with an exception
            _loginState.value = Resource.Error(e.message ?: "Unknown error occurred")
        }
    }
}
