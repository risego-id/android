package com.rg.capstone.ui.screen.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rg.capstone.repository.RGRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel@Inject constructor(
    private val repository: RGRepository
): ViewModel() {

    private val _userToken = MutableStateFlow<String?>("")
    val userToken: StateFlow<String?> get() = _userToken

    init {
//        repository.getUser().observeForever{
//            _userToken.value = it
//        }
        viewModelScope.launch {
            repository.getUserToken().collect { token ->
                _userToken.value = token
                Log.e(TAG, "userToken HomeViewModel $token")
            }
        }
    }
}