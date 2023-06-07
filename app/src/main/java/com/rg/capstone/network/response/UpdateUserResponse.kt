package com.rg.capstone.network.response

import com.google.gson.annotations.SerializedName
import com.rg.capstone.domain.model.User

data class UpdateUserResponse (
    @SerializedName("status")
    var status: Boolean,

    @SerializedName("message")
    var message: String,

    @SerializedName("user")
    var user: User,
)