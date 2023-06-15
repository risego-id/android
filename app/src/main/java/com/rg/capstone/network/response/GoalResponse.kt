package com.rg.capstone.network.response

import com.google.gson.annotations.SerializedName
import com.rg.capstone.network.model.GoalDto

data class GetGoalResponse (
    @SerializedName("status")
    val status : Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val goals: List<GoalDto>,
)

data class AddGoalResponse (
    @SerializedName("status")
    val status: Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: GoalDto,
)

data class EditGoalResponse (
    @SerializedName("status")
    val status: Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: GoalDto,
)

data class DeleteGoalResponse (
    @SerializedName("status")
    val status: Boolean,

    @SerializedName("message")
    val message: String
)