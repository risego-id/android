package com.rg.capstone.network

import com.rg.capstone.network.model.UserDto
import com.rg.capstone.network.response.FoodRecResponse
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
    @GET("auth/user/update")
    suspend fun updateUserInfo(
        @Header("Authorization") token: String,
        @Field("weight") weight: Int,
        @Field("height") height: Int,
        @Field("gender") gender: String,
        @Field("data_of_birth") date_of_birth: String,
    ): UpdateUserResponse

    @POST("recommendation/food")
    suspend fun getRecommendation(
        @Header("Authorization") token: String,
    ): FoodRecResponse

    @POST("auth/logout")
    suspend fun logoutUser(
        @Header("Authorization") token: String,
    ): LogoutResponse
}