package com.rg.capstone.network.response

import com.google.gson.annotations.SerializedName
import com.rg.capstone.domain.model.User
import com.rg.capstone.network.model.UserDto

data class LoginResponse (
    @SerializedName("status")
    var status: Boolean,

    @SerializedName("message")
    var message: String,

    @SerializedName("token")
    var token: String,

    @SerializedName("user")
    var user: UserDto,
)