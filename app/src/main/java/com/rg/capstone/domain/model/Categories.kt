package com.rg.capstone.domain.model

import com.rg.capstone.R

data class Category(
    val id: Int,
    val title: String,
    val description: String,
    val image: Int,
    val backgroundColor: Int,
    val fontColor: Int,
)

object CategoriesData {
    val categories = listOf(
        Category (
            1,
            "Health",
            "Look here for any health related recommendation that might be helpful for you!,",
            R.drawable.health_recommendation,
            R.color.dark_blue,
            R.color.light_blue
        ),
        Category(
            2,
            "Skill",
            "Look here for any health related recommendation that might be helpful for you!",
            R.drawable.skills_recommendation,
            R.color.dark_yellow,
            R.color.light_yellow
        ),
        Category(
            3,
            "Finance",
            "Look here for any health related recommendation that might be helpful for you!",
            R.drawable.finance_recommendation,
            R.color.dark_green,
            R.color.light_green
        )
    )
}