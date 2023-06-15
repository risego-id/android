package com.rg.capstone.domain.model

import java.util.Date


data class Goal(
    val title: String,
    val type: String, // health finance etc
    val task: List<Task>,
)

data class Task (
    val title: String,
    val isDone: Boolean,
    val image: Int,
    val taskType: String, //day, week, month, year
    val start: Date,
    val end: Date
)

//object GoalsData {
//    val goals = listOf<Goal>(
//        Goal(
//            image
//        )
//    )
//}
