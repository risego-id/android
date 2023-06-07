package com.rg.capstone.network.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse (
    @SerializedName("status")
    var status: Boolean,

    @SerializedName("message")
    var message: String,

    @SerializedName("token")
    var token: String,
)