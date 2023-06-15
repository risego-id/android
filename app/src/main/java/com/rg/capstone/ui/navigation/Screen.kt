package com.rg.capstone.ui.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.parcelize.Parcelize

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Goal : Screen("goal")
    object Collaboration : Screen("collaboration")
    object Streak : Screen("streak")
    object Login : Screen("login")
    object Register : Screen("register")
    object ListGoal : Screen("list_goal")
    object UpdateUser: Screen("update_user")
    object AddGoal: Screen("add_goal/{category}") {
        fun createRoute(category: String) = "add_goal/$category"
    }
    object Recommendation: Screen("recommendation/{category}/{goal}") {
        fun createRoute(category: String, goal: String) = "recommendation/$category/$goal"
    }
    object AddTask: Screen("add_task/{category}/{goal}/{tasks}") {
        fun createRoute(category: String, goal: String, tasks: List<String>) = "add_task/$category/$goal/${tasks.joinToString(",")}"
    }
}