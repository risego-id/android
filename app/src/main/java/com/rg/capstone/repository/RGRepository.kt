package com.rg.capstone.repository

import androidx.lifecycle.LiveData
import com.rg.capstone.network.response.LoginResponse
import com.rg.capstone.network.Resource
import com.rg.capstone.network.model.UserDto
import com.rg.capstone.network.response.LogoutResponse
import com.rg.capstone.network.response.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface RGRepository {
    suspend fun login(email: String, password: String): LiveData<Resource<LoginResponse>>

    suspend fun register(email: String, name: String, password: String, password_confirm: String): LiveData<Resource<RegisterResponse>>

    suspend fun logout(token: String): LiveData<Resource<LogoutResponse>>

    suspend fun getUser(token: String): LiveData<Resource<UserDto>>

    suspend fun getToken(token: String): String

    fun getUserToken(): Flow<String?>

    fun getUserId(): Flow<Int?>
}