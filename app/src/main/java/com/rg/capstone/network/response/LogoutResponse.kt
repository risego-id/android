package com.rg.capstone.network.response

import com.google.gson.annotations.SerializedName

data class LogoutResponse (
    @SerializedName("message")
    val message: String,
)