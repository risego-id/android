package com.rg.capstone.network.response

import com.google.gson.annotations.SerializedName

data class FoodRecResponse (
    @SerializedName("status")
    val status: Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: Data? = null
)

data class Data (
    @SerializedName("recommendations")
    val recommendations: List<String>,

    @SerializedName("recommendations_2")
    val recommendations_2: List<String>,
)