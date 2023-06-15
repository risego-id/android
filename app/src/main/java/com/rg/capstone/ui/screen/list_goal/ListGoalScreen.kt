package com.rg.capstone.ui.screen.list_goal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rg.capstone.BottomBar
import com.rg.capstone.R
import com.rg.capstone.network.Resource
import com.rg.capstone.ui.component.ButtonItem
import com.rg.capstone.ui.component.TitleScreenItem
import com.rg.capstone.ui.navigation.Screen
import com.rg.capstone.ui.theme.SpaceMedium

@Composable
fun ListGoalScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    viewModel: ListGoalViewModel = hiltViewModel(),
) {
    val listGoalState by viewModel.listGoalState.collectAsState()
    val userToken by viewModel.userToken.collectAsState()

    if (userToken != null) {
        if (userToken!!.isNotEmpty()) {
            LaunchedEffect(Unit) {
                viewModel.getGoalList(userToken!!)
            }
        }
    }

    Column(modifier = modifier
        .padding(SpaceMedium)
        .fillMaxSize()){
        TitleScreenItem(title = R.string.health_goals, onBackClick = { navController.navigate(Screen.Goal.route) })
        Column(modifier = modifier
            .weight(.8f)
            .verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
            when (val response = listGoalState) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    val listGoal = response.data.goals
                    if (listGoal != null) {
                        for (goal in listGoal) {
                            Box(
                                modifier = modifier
                                    .background(Color.White)
                                    .border(
                                        width = 2.dp,
                                        color = Color.LightGray,
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .clip(RoundedCornerShape(10.dp))
                                    .padding(SpaceMedium)
                                    .fillMaxWidth()
                            ) {
                                Column(modifier = modifier){
                                    Text(text = goal.title)
                                    for (task in goal.task) {
                                        Text(text = task.title)
                                    }
                                }
//                                Text(text = goal.task)
                            }
                            Spacer(modifier = modifier.height(SpaceMedium))
                        }
                    } else {
                        Text(
                            text = stringResource(id = R.string.no_goal),
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Color.LightGray,
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp
                            ),
                            modifier = modifier.fillMaxSize(), textAlign = TextAlign.Center
                        )
                    }
                }
                is Resource.Error -> {}
                else -> {}
            }
        }
        ButtonItem(
            text = stringResource(id = R.string.add_new_goal),
            onClick = {
                navController.navigate(
                    Screen.AddGoal.createRoute("health")
                )
            },
        )
    }

}
