package com.rg.capstone.network

import com.rg.capstone.network.model.UserDto
import com.rg.capstone.network.response.FoodRecResponse
import com.rg.capstone.network.response.LoginResponse
import com.rg.capstone.network.response.RegisterResponse
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

    @POST("recommendation/food")
    suspend fun getRecommendation(
        @Header("Authorization") token: String,
    ): FoodRecResponse
}