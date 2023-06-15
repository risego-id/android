package com.rg.capstone.ui.screen.add_task

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rg.capstone.network.Resource
import com.rg.capstone.network.response.AddGoalResponse
import com.rg.capstone.network.response.AddTaskResponse
import com.rg.capstone.repository.RGRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val repository: RGRepository
): ViewModel() {
    private val _taskText = mutableStateOf("")
    val taskText: State<String> get() = _taskText

    private val _typeText = mutableStateOf("")
    val typeText: State<String> get() = _typeText

    private val _startDateText = mutableStateOf("")
    val startDateText: State<String> get() = _startDateText

    private val _endDateText = mutableStateOf("")
    val endDateText: State<String> get() = _endDateText

    private val _selectedType = mutableStateOf("")
    val selectedType: State<String> get() = _selectedType

    private val _userToken = MutableStateFlow<String?>("")
    val userToken: StateFlow<String?> get() = _userToken

    private val _addGoalState = MutableStateFlow<Resource<AddGoalResponse>>(Resource.Empty)
    val addGoalState: StateFlow<Resource<AddGoalResponse>> = _addGoalState

    private val _addTaskState = MutableStateFlow<Resource<AddTaskResponse>>(Resource.Empty)
    val addTaskState: StateFlow<Resource<AddTaskResponse>> = _addTaskState

    init {
        viewModelScope.launch {
            repository.getUserToken().collect {
                _userToken.value = it
            }
        }
    }

    fun setTaskText(taskName: String) {
        _taskText.value = taskName
    }

    fun setSelectedType(type: String) {
        _selectedType.value = type
    }

    fun setStartDate(startDate: String) {
        _startDateText.value = startDate
    }

    fun setEndDate(endDate: String) {
        _endDateText.value = endDate
    }

    fun addGoal(tkn: String, title: String, category: String) = viewModelScope.launch {
        _addGoalState.value = Resource.Loading
        try {
            val responseLiveData = repository.addGoal(tkn, title, category)
            responseLiveData.observeForever { response ->
                if (response is Resource.Success<AddGoalResponse>) {
                    _addGoalState.value = Resource.Success(response.data)
                } else if (response is Resource.Error) {
                    _addGoalState.value = Resource.Error(response.message)
                }
            }
        } catch (e: Exception) {
            _addGoalState.value = Resource.Error(e.message ?: "Unknown error occurred")
        }
    }

    fun addTask(
        token: String,
        goal_id: Int,
        title: String,
        is_done: Int,
        task_type: String,
        start_date: String,
        end_date: String
    ) = viewModelScope.launch {
        _addTaskState.value = Resource.Loading
        try {
            val responseLiveData = repository.addTask(token, goal_id, title, is_done, task_type, start_date, end_date)
            responseLiveData.observeForever { response ->
                if (response is Resource.Success<AddTaskResponse>) {
                    _addTaskState.value = Resource.Success(response.data)
                } else if (response is Resource.Error) {
                    _addTaskState.value = Resource.Error(response.message)
                }
            }
        } catch (e: Exception) {
            _addTaskState.value = Resource.Error(e.message ?: "Unknown error occurred")
        }
    }
}