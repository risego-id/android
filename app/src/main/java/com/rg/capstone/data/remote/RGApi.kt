package com.rg.capstone.data.remote

import retrofit2.http.GET

interface RGApi {
    @GET("test")
    suspend fun doNetworkCall()
}