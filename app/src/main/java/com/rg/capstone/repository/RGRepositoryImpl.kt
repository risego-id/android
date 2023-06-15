package com.rg.capstone.repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import com.rg.capstone.domain.model.Category
import com.rg.capstone.network.ApiService
import com.rg.capstone.network.model.UserDtoMapper
import com.rg.capstone.network.response.LoginResponse
import com.rg.capstone.ui.common.UiState
import androidx.lifecycle.liveData
import com.rg.capstone.domain.UserPreference
import com.rg.capstone.network.Resource
import com.rg.capstone.network.model.UserDto
import com.rg.capstone.network.response.AddGoalResponse
import com.rg.capstone.network.response.AddTaskResponse
import com.rg.capstone.network.response.FoodRecResponse
import com.rg.capstone.network.response.GetGoalResponse
import com.rg.capstone.network.response.LogoutResponse
import com.rg.capstone.network.response.RegisterResponse
import com.rg.capstone.network.response.UpdateUserResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf

class RGRepositoryImpl(
    private val api: ApiService,
    private val userDtoMapper: UserDtoMapper,
    private val pref: UserPreference
): RGRepository {

    private val category = mutableListOf<Category>()

    private val _uiState: MutableStateFlow<UiState<LoginResponse>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<LoginResponse>> get() = _uiState

    init {
    }

    override suspend fun login(
        email: String,
        password: String
    ): LiveData<Resource<LoginResponse>> = liveData{
        try {
            emit(Resource.Loading)
            val response = api.loginUser(email, password)
            val token = response.token
            val id = response.user.id
            pref.saveUser(token, id)
            Log.e(TAG,"Login success: $token")
            emit(Resource.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.toString()))
            Log.e(TAG,"Login Error: ${e.toString()}")
        }
    }

    override suspend fun register(
        email: String,
        name: String,
        password: String,
        password_confirm: String,
    ): LiveData<Resource<RegisterResponse>> = liveData{
        try {
            emit(Resource.Loading)
            val response = api.registerUser(email = email, password = password, name = name, password_confirmation = password_confirm)
            val token = response.token
            val getUser = api.getUserInfo("Bearer $token")
            val id = getUser.id
            pref.saveUser(token, id)
            Log.e(TAG,"Register success: $token")
            emit(Resource.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.toString()))
            Log.e(TAG,"Register Error: ${e.toString()}")
        }
    }

    override suspend fun getUser(tkn: String): LiveData<Resource<UserDto>> = liveData {
        try {
            emit(Resource.Loading)
            val token = getToken(tkn)
            val response = api.getUserInfo(token)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.toString()))
        }
    }

    override suspend fun logout(tkn: String): LiveData<Resource<LogoutResponse>> = liveData {
        try {
            emit(Resource.Loading)
            val token= getToken(tkn)
            val response = api.logoutUser(token)
            pref.logout()
            emit(Resource.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.toString()))
        }
    }

    override suspend fun updateUser(
        tkn: String,
        weight: Int,
        height: Int,
        gender: String,
        date: String
    ): LiveData<Resource<UpdateUserResponse>> = liveData {
        try {
            emit(Resource.Loading)
            val token = getToken(tkn)
            val response = api.updateUserInfo(token = token, weight = weight, height = height, date_of_birth = date, gender = gender)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.toString()))
        }
    }

    override suspend fun getToken(token: String): String = "Bearer $token"
    override suspend fun getFoodRecommendation(tkn: String): LiveData<Resource<FoodRecResponse>> = liveData {
        try {
            emit(Resource.Loading)
            val token = getToken(tkn)
            val response = api.getRecommendation(token)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.toString()))
        }
    }

    override suspend fun addGoal(
        tkn: String,
        title: String,
        category: String
    ): LiveData<Resource<AddGoalResponse>> = liveData {
        try {
            emit(Resource.Loading)
            val token = getToken(tkn)
            val response = api.addGoal(token, title, category)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.toString()))
        }
    }

    override suspend fun addTask(
        tkn: String,
        goal_id: Int,
        title: String,
        is_done: Int,
        task_type: String,
        start_date: String,
        end_date: String
    ): LiveData<Resource<AddTaskResponse>> = liveData {
        try {
            emit(Resource.Loading)
            val token = getToken(tkn)
            val response = api.addTask(token, goal_id, title, is_done, task_type, start_date, end_date)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.toString()))
        }
    }

    override suspend fun getGoal(tkn: String): LiveData<Resource<GetGoalResponse>> = liveData {
        try {
            emit(Resource.Loading)
            val token = getToken(tkn)
            val response = api.getGoal(token)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.toString()))
        }
    }

    override fun getUserToken(): Flow<String?> = pref.getUserToken()

    override fun getUserId(): Flow<Int?> = pref.getUserId()

}