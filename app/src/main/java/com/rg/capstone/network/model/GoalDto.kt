package com.rg.capstone.network.model

import com.google.gson.annotations.SerializedName

data class GoalDto (
    @SerializedName("id")
    var id: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("type")
    var type: String,

    @SerializedName("user_id")
    var userId: Int,

    @SerializedName("task")
    var task: List<TaskDto>
)