package com.rg.capstone.repository

import androidx.lifecycle.LiveData
import com.rg.capstone.network.response.LoginResponse
import com.rg.capstone.network.Resource
import com.rg.capstone.network.model.UserDto
import com.rg.capstone.network.response.AddGoalResponse
import com.rg.capstone.network.response.AddTaskResponse
import com.rg.capstone.network.response.FoodRecResponse
import com.rg.capstone.network.response.GetGoalResponse
import com.rg.capstone.network.response.LogoutResponse
import com.rg.capstone.network.response.RegisterResponse
import com.rg.capstone.network.response.UpdateUserResponse
import kotlinx.coroutines.flow.Flow

interface RGRepository {
    suspend fun login(email: String, password: String): LiveData<Resource<LoginResponse>>

    suspend fun register(email: String, name: String, password: String, password_confirm: String): LiveData<Resource<RegisterResponse>>

    suspend fun logout(token: String): LiveData<Resource<LogoutResponse>>

    suspend fun updateUser(token: String, weight: Int, height: Int, gender: String, date: String): LiveData<Resource<UpdateUserResponse>>

    suspend fun getUser(token: String): LiveData<Resource<UserDto>>

    suspend fun getToken(token: String): String

    suspend fun getFoodRecommendation(token: String): LiveData<Resource<FoodRecResponse>>

//    fun selectTask(food: String, newState: Boolean, foods: List<String>): Flow<Boolean>

    suspend fun addGoal(token: String, title: String, category: String): LiveData<Resource<AddGoalResponse>>

    suspend fun addTask(token: String, goal_id: Int, title: String, is_done: Int, task_type: String, start_date: String, end_date: String): LiveData<Resource<AddTaskResponse>>

    suspend fun getGoal(token: String): LiveData<Resource<GetGoalResponse>>

    fun getUserToken(): Flow<String?>

    fun getUserId(): Flow<Int?>
}