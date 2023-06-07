package com.rg.capstone.ui.screen.goal_category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rg.capstone.domain.model.Category
import com.rg.capstone.repository.RGRepository
import com.rg.capstone.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalCategoryViewModel @Inject constructor(
    private val repository: RGRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Category>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Category>>> get() = _uiState

    fun getAllGoalCategory() {
        viewModelScope.launch {
//            repository.
        }
    }
}