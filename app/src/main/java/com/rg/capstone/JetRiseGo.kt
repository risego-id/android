package com.rg.capstone

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rg.capstone.ui.navigation.NavigationItem
import com.rg.capstone.ui.navigation.Screen
import com.rg.capstone.ui.screen.collaboration.CollaborationScreen
import com.rg.capstone.ui.screen.goal_category.GoalCategoryScreen
import com.rg.capstone.ui.screen.home.HomeScreen
import com.rg.capstone.ui.screen.list_goal.ListGoalScreen
import com.rg.capstone.ui.screen.login.LoginScreen
import com.rg.capstone.ui.screen.profile.ProfileScreen
import com.rg.capstone.ui.screen.register.RegisterScreen
import com.rg.capstone.ui.screen.streak.StreakScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JetRiseGo(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in listOf(
                    Screen.Collaboration.route,
                    Screen.Goal.route,
                    Screen.Home.route,
                    Screen.Profile.route,
                    Screen.Streak.route
            ) ) {
                BottomBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(modifier = modifier, navController = navController)
            }
            composable(Screen.Login.route) {
                LoginScreen(modifier = modifier, navController = navController)
            }
            composable(Screen.Register.route) {
                RegisterScreen(modifier = modifier, navController = navController)
            }
            composable(Screen.Goal.route) {
                GoalCategoryScreen(modifier = modifier, navController = navController)
            }
            composable(Screen.Streak.route) {
                StreakScreen(modifier = modifier)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(modifier = modifier, navController = navController)
            }
            composable(Screen.Collaboration.route) {
                CollaborationScreen(modifier = modifier)
            }
            composable(Screen.ListGoal.route) {
                ListGoalScreen(modifier = modifier, navController = navController)
            }
        }

    }


}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItem = listOf(
            NavigationItem(
                title = stringResource(id = R.string.home),
                icon = Icons.Default.Home,
                screen = Screen.Home,
                contentDescription = stringResource(id = R.string.home_page)
            ),
            NavigationItem(
                title = stringResource(id = R.string.goals),
                icon = Icons.Default.List,
                screen = Screen.Goal,
                contentDescription = stringResource(id = R.string.goal_page)
            ),
            NavigationItem(
                title = stringResource(id = R.string.collaboration),
                icon = Icons.Default.People,
                screen = Screen.Collaboration,
                contentDescription = stringResource(id = R.string.collab_page)
            ),
            NavigationItem(
                title = stringResource(id = R.string.streak),
                icon = Icons.Default.CalendarMonth,
                screen = Screen.Streak,
                contentDescription = stringResource(id = R.string.streak_page)
            ),
            NavigationItem(
                title = stringResource(id = R.string.profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile,
                contentDescription = stringResource(id = R.string.profile_page)
            ),
        )
        NavigationBar() {
            navigationItem.map { item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.contentDescription
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 8.sp
                            )
                            )},
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}