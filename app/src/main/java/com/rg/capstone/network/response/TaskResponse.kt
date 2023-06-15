package com.rg.capstone.network.response

import com.google.gson.annotations.SerializedName
import com.rg.capstone.network.model.TaskDto

data class AddTaskResponse (
    @SerializedName("status")
    val status: Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: TaskDto
)

data class EditTaskResponse (
    @SerializedName("status")
    val status: Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: TaskDto,
)

data class DeleteTaskResponse (
    @SerializedName("status")
    val status: Boolean,

    @SerializedName("message")
    val message: String,
)