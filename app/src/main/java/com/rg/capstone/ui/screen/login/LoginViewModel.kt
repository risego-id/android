package com.rg.capstone.ui.screen.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.rg.capstone.repository.RGRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: RGRepository
): ViewModel() {
    private val _emailText = mutableStateOf("")
    val emailText: State<String> = _emailText

    private val _passwordText = mutableStateOf("")
    val passwordText: State<String> = _passwordText

    private val _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> = _showPassword

    private val _emailError = mutableStateOf("")
    val emailError: State<String> = _emailError

    private val _passwordError = mutableStateOf("")
    val passwordError: State<String> = _passwordError

    fun setEmailText(email: String) {
        _emailText.value = email
    }

    fun setPasswordText(password: String) {
        _passwordText.value = password
    }

    fun setShowPassword(showPassword: Boolean) {
        _showPassword.value = showPassword
    }
}
