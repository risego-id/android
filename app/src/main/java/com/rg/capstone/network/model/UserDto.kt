package com.rg.capstone.network.model

import com.google.gson.annotations.SerializedName

data class UserDto (
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("email")
    var email: String,

    @SerializedName("height")
    var height: Int? = 0,

    @SerializedName("weight")
    var weight: Int? = 0,

    @SerializedName("gender")
    var gender: String? = "",

    @SerializedName("date_of_birth")
    var dob: String? = "",

    @SerializedName("age")
    var age: Int? = 0,
)