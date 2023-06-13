package com.rg.capstone.ui.screen.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rg.capstone.network.Resource
import com.rg.capstone.network.response.RegisterResponse
import com.rg.capstone.repository.RGRepository
import com.rg.capstone.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: RGRepository
): ViewModel() {
    private val _usernameText = mutableStateOf("")
    val usernameText: State<String> = _usernameText

    private val _emailText = mutableStateOf("")
    val emailText: State<String> = _emailText

    private val _passwordText = mutableStateOf("")
    val passwordText: State<String> = _passwordText

    private val _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> = _showPassword

    private val _showPasswordConfirm = mutableStateOf(false)
    val showPasswordConfirm: State<Boolean> = _showPasswordConfirm

    private val _emailError = mutableStateOf("")
    val emailError: State<String> = _emailError

    private val _usernameError = mutableStateOf("")
    val usernameError: State<String> = _usernameError

    private val _passwordError = mutableStateOf("")
    val passwordError: State<String> = _passwordError

    private val _passwordConfirm = mutableStateOf("")
    val passwordConfirm: State<String> = _passwordConfirm

    private val _registerState = MutableStateFlow<Resource<RegisterResponse>>(Resource.Empty)
    val registerState: StateFlow<Resource<RegisterResponse>> = _registerState

    fun setUsernameText(username: String) {
        _usernameText.value = username
    }

    fun setEmailText(email: String) {
        _emailText.value = email
    }

    fun setPasswordText(password: String) {
        _passwordText.value = password
    }

    fun setShowPassword(showPassword: Boolean) {
        _showPassword.value = showPassword
    }

    fun setShowPasswordConfirm(showPassword: Boolean) {
        _showPasswordConfirm.value = showPassword
    }

    fun setPasswordConfirmText(password_confirm: String) {
        _passwordConfirm.value = password_confirm
    }

    fun userRegister(name: String, email: String, password: String, password_confirm: String) = viewModelScope.launch {
        _registerState.value = Resource.Loading
        try {
            val response = repository.register(email, name, password, password_confirm)
            response.observeForever { rsp ->
                if (rsp is Resource.Success<RegisterResponse>) {
                    _registerState.value = Resource.Success(rsp.data)
                } else if (rsp is Resource.Error) {
                    _registerState.value = Resource.Error(rsp.message)
                }
            }
        } catch (e: Exception) {
            _registerState.value = Resource.Error(e.message ?: "Unknown error occurred")
        }
    }
}