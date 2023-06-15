package com.rg.capstone.network.model

import com.google.gson.annotations.SerializedName

data class TaskDto (
    @SerializedName("goal_id")
    var goalId: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("is_done")
    var isDone: Int = 0,

    @SerializedName("task_type")
    var taskType: String,

    @SerializedName("start_date")
    var startDate: String,

    @SerializedName("end_date")
    var endDate: String,

    @SerializedName("id")
    var id: Int
)