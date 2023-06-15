package com.rg.capstone.network

import com.rg.capstone.network.model.UserDto
import com.rg.capstone.network.response.AddGoalResponse
import com.rg.capstone.network.response.AddTaskResponse
import com.rg.capstone.network.response.DeleteGoalResponse
import com.rg.capstone.network.response.DeleteTaskResponse
import com.rg.capstone.network.response.EditGoalResponse
import com.rg.capstone.network.response.EditTaskResponse
import com.rg.capstone.network.response.FoodRecResponse
import com.rg.capstone.network.response.GetGoalResponse
import com.rg.capstone.network.response.LoginResponse
import com.rg.capstone.network.response.LogoutResponse
import com.rg.capstone.network.response.RegisterResponse
import com.rg.capstone.network.response.UpdateUserResponse
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse

    @FormUrlEncoded
    @POST("auth/register")
    suspend fun registerUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") password_confirmation: String,
    ): RegisterResponse

    @GET("auth/user")
    suspend fun getUserInfo(
        @Header("Authorization") token: String,
    ): UserDto

    @FormUrlEncoded
    @POST("auth/user/update")
    suspend fun updateUserInfo(
        @Header("Authorization") token: String,
        @Field("weight") weight: Int,
        @Field("height") height: Int,
        @Field("gender") gender: String,
        @Field("date_of_birth") date_of_birth: String,
    ): UpdateUserResponse

    @POST("auth/logout")
    suspend fun logoutUser(
        @Header("Authorization") token: String,
    ): LogoutResponse

    //Food recommendation
    @POST("recommendation/food")
    suspend fun getRecommendation(
        @Header("Authorization") token: String,
    ): FoodRecResponse

    // Goal
    @GET("goal")
    suspend fun getGoal(
        @Header("Authorization") token: String,
    ): GetGoalResponse

    @FormUrlEncoded
    @POST("goal")
    suspend fun addGoal(
        @Header("Authorization") token: String,
        @Field("title") title: String,
        @Field("type") type: String,
    ) : AddGoalResponse

    @FormUrlEncoded
    @PUT("goal/{id}")
    suspend fun editGoal(
        @Header("Authorization") token: String,
        @Field("title") title: String,
        @Field("type") type: String,
    ) : EditGoalResponse

    @DELETE("goal/{id}")
    suspend fun deleteGoal (
        @Header("Authorization") token: String,
    ): DeleteGoalResponse

    // Task
    @FormUrlEncoded
    @POST("task")
    suspend fun addTask (
        @Header("Authorization") token: String,
        @Field("goal_id") goal_id: Int,
        @Field("title") title: String,
        @Field("is_done") is_done: Int,
        @Field("task_type") task_type: String,
        @Field("start_date") start_date: String,
        @Field("end_date") end_date: String
    ): AddTaskResponse

    @FormUrlEncoded
    @PUT("task/{id}")
    suspend fun editTask (
        @Header("Authorization") token: String,
        @Field("title") title: String,
        @Field("is_done") is_done: Boolean,
        @Field("task_type") task_type: String,
        @Field("start_date") start_date: String,
        @Field("end_date") end_date: String,
    ): EditTaskResponse

    @DELETE("task/{id}")
    suspend fun deleteTask (
        @Header("Authorization") token: String,
    ): DeleteTaskResponse
}