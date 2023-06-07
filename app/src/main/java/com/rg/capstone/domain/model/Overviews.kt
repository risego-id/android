package com.rg.capstone.domain.model

import com.rg.capstone.R

data class Overviews(
    val id: Int,
    val title: String,
    val percentage: Int,
    val textColor: Int,
    val backgroundColor: Int,
)

object OverviewData {
    val overviews = listOf<Overviews>(
        Overviews(
            1,
            "Health",
            69,
            R.color.dark_blue,
            R.color.light_blue
        ),
        Overviews(
            2,
            "Finance",
            70,
            R.color.dark_pink,
            R.color.light_pink
        ),
        Overviews(
            3,
            "Skill",
            80,
            R.color.dark_yellow,
            R.color.light_yellow
        )
    )
}