package com.rg.capstone.ui.screen.list_goal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rg.capstone.network.Resource
import com.rg.capstone.network.response.GetGoalResponse
import com.rg.capstone.repository.RGRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListGoalViewModel @Inject constructor(
    private val repository: RGRepository
): ViewModel() {
    private val _userToken = MutableStateFlow<String?>("")
    val userToken: StateFlow<String?> get() = _userToken

    private val _listGoalState = MutableStateFlow<Resource<GetGoalResponse>>(Resource.Empty)
    val listGoalState: StateFlow<Resource<GetGoalResponse>> = _listGoalState

    init {
        viewModelScope.launch {
            repository.getUserToken().collect { token ->
                _userToken.value = token
            }
        }
    }

    fun getGoalList(token: String) = viewModelScope.launch {
        _listGoalState.value = Resource.Loading
        try {
            val response = repository.getGoal(token)
            response.observeForever { res ->
                if (res is Resource.Success<GetGoalResponse>) {
                    _listGoalState.value = Resource.Success(res.data)
                } else if (res is Resource.Error) {
                    _listGoalState.value = Resource.Error(res.message)
                }
            }
        } catch (e: Exception) {
            _listGoalState.value = Resource.Error(e.message ?: "Unknown Error Occurred")
        }
    }
}