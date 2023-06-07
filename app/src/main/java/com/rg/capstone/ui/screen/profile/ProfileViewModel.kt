package com.rg.capstone.ui.screen.profile

import androidx.lifecycle.ViewModel
import com.rg.capstone.repository.RGRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: RGRepository
): ViewModel() {

}