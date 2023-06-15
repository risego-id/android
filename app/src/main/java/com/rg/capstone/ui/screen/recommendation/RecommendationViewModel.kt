package com.rg.capstone.ui.screen.recommendation

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rg.capstone.network.Resource
import com.rg.capstone.network.model.UserDto
import com.rg.capstone.network.response.FoodRecResponse
import com.rg.capstone.repository.RGRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendationViewModel  @Inject constructor(
    private val repository: RGRepository
): ViewModel() {
    private val _userToken = MutableStateFlow<String?>("")
    val userToken: StateFlow<String?> get() = _userToken

    private val _foodRecState = MutableStateFlow<Resource<FoodRecResponse>>(Resource.Empty)
    val foodRecState: StateFlow<Resource<FoodRecResponse>> get() = _foodRecState

    private val _userInfo = MutableStateFlow<Resource<UserDto?>>(Resource.Empty)
    val userInfo: StateFlow<Resource<UserDto?>> get() = _userInfo

    private val _selectedTasks = MutableStateFlow<List<String>>(emptyList())
    val selectedTasks: StateFlow<List<String>> = _selectedTasks

    init {
        viewModelScope.launch {
            repository.getUserToken().collect {
                _userToken.value = it
            }
        }
    }

    fun getFoodRecommendation(token: String) = viewModelScope.launch {
        _foodRecState.value = Resource.Loading
        try {
            val response = repository.getFoodRecommendation(token)
            response.observeForever { res ->
                if (res is Resource.Success<FoodRecResponse>) {
                    _foodRecState.value = Resource.Success(res.data)
                    _selectedTasks.value = res.data.data?.recommendations ?: emptyList()
                } else if (res is Resource.Error) {
                    _foodRecState.value = Resource.Error(res.message)
                }
            }
        } catch (e: Exception) {
            _foodRecState.value = Resource.Error(e.message ?: "Unknown Error Occurred")
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

    fun taskSelection(task: String) {
        val updatedList = selectedTasks.value.toMutableList()
        if (updatedList.contains(task)) {
            updatedList.remove(task)
        } else {
            updatedList.add(task)
        }
        _selectedTasks.value = updatedList
//        Log.d("selected food", "food: ${_selectedTasks.value}")
    }
}