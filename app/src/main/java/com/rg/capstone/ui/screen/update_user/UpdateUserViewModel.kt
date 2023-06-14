package com.rg.capstone.ui.screen.update_user

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.rg.capstone.repository.RGRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.rg.capstone.network.Resource
import com.rg.capstone.network.response.UpdateUserResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class UpdateUserViewModel @Inject constructor(
    private val repository: RGRepository,
): ViewModel() {
    private val _heightText = mutableStateOf("")
    val heightText: State<String> get() = _heightText

    private val _weightText = mutableStateOf("")
    val weightText: State<String> get() = _weightText

    private val _dateText = mutableStateOf("")
    val dateText: State<String> get() = _dateText

    private val _selectedGender = mutableStateOf("")
    val selectedGender: State<String> get() = _selectedGender

    private val _userToken = MutableStateFlow<String?>("")
    val userToken: StateFlow<String?> get() = _userToken

    private val _updateState = MutableStateFlow<Resource<UpdateUserResponse>>(Resource.Empty)
    val updateState: StateFlow<Resource<UpdateUserResponse>> = _updateState

    init {
        viewModelScope.launch {
            repository.getUserToken().collect { token ->
                _userToken.value = token
            }
        }
    }

    fun setHeightText(height: String) {
        _heightText.value = height
    }

    fun setWeightText(weight: String) {
        _weightText.value = weight
    }

    fun setDateText(date: String) {
        _dateText.value = date
    }

    fun setSelectedGender(gender: String) {
        _selectedGender.value = gender
    }

    fun updateUser(token: String, weight: Int, height: Int, gender: String, date: String) = viewModelScope.launch {
        _updateState.value = Resource.Loading
        try {
            val responseLiveData = repository.updateUser(token, weight, height, gender, date)
            responseLiveData.observeForever {res ->
                if (res is Resource.Success<UpdateUserResponse>) {
                    _updateState.value = Resource.Success(res.data)
                } else if (res is Resource.Error) {
                    _updateState.value = Resource.Error(res.message)
                }
            }
        }catch (e: Exception) {
            _updateState.value = Resource.Error(e.message ?: "Unknown error occurred")
        }
    }
}