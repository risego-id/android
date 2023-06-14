package com.rg.capstone.ui.navigation

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
}