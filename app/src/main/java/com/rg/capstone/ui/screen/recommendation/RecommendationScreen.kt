package com.rg.capstone.ui.screen.recommendation

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rg.capstone.R
import com.rg.capstone.network.Resource
import com.rg.capstone.ui.component.ButtonItem
import com.rg.capstone.ui.component.RecTileItem
import com.rg.capstone.ui.component.TitleScreenItem
import com.rg.capstone.ui.navigation.Screen
import com.rg.capstone.ui.theme.SpaceLarge
import com.rg.capstone.ui.theme.SpaceMedium
import com.rg.capstone.ui.theme.SpaceSmall

@Composable
fun RecommendationScreen(
    category: String,
    goal: String,
    navController: NavController,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    viewModel: RecommendationViewModel = hiltViewModel()
) {
    val userInfo by viewModel.userInfo.collectAsState()
    val userToken by viewModel.userToken.collectAsState()
    val foodRec by viewModel.foodRecState.collectAsState()
    val selected by viewModel.selectedTasks.collectAsState()

    if (userToken != null) {
        if (userToken!!.isNotEmpty()) {
            LaunchedEffect(Unit){
                viewModel.getFoodRecommendation(userToken!!)
                viewModel.getUserInfo(userToken!!)
            }
        }
    } else {
        navController.navigate(Screen.Login.route)
    }

    Column(modifier = modifier.padding(SpaceMedium)) {
        TitleScreenItem(title = R.string.recommendation, onBackClick = { navController.navigateUp() })
        Spacer(modifier = modifier.height(SpaceMedium))
        Text(
            text = stringResource(id = R.string.recommendation_description),
            style = MaterialTheme.typography.labelMedium.copy(
                textAlign = TextAlign.Justify
            )
        )
        Spacer(modifier = modifier.height(SpaceMedium))
        Text(
            text = stringResource(id = R.string.premium_first),
            style = MaterialTheme.typography.labelMedium.copy(
                color = colorResource(id = R.color.dark_red)
            )
        )
        Spacer(modifier = modifier.height(SpaceLarge))
        Box(modifier = modifier.weight(.6f)) {
            when (val response = userInfo) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    val userInfo = response.data
                    if (userInfo != null) {
                        if (userInfo.height == null || userInfo.weight == null) {
                            OutlinedButton(
                                onClick = { navController.navigate(Screen.UpdateUser.route) },
                                modifier = modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = stringResource(R.string.update_profile),
                                    modifier = modifier.padding(SpaceSmall),
                                    color = colorResource(id = R.color.dark_red)
                                )
                            }
                        } else {
                            when (val rec = foodRec) {
                                is Resource.Loading -> {
                                    CircularProgressIndicator(modifier = modifier.align(Alignment.Center).fillMaxWidth())
                                }
                                is Resource.Success -> {
                                    val foods = rec.data.data?.recommendations
                                    LazyColumn(
                                        modifier = modifier,
                                        verticalArrangement = Arrangement.spacedBy(SpaceSmall),
                                    ) {
                                        if (foods != null) {
                                            items(foods.size){
//                                                viewModel.addSelectedTask(foods[it])
                                                val isSelected = selected.contains(foods[it])
                                                RecTileItem(
                                                    isSelected = isSelected,
                                                    title = foods[it],
                                                    modifier = modifier.clickable { viewModel.taskSelection(foods[it]) }
                                                )
                                            }
                                            Log.d("bundle", "category: $category, goal: $goal, task: $selected")
                                        }
                                    }
                                }
                                is Resource.Error -> {
                                    Text(
                                        text = "Login Failed: ${rec.message}",
                                        color = Color.Red,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                } else -> {}
                            }
                        }
                    }
                }
                is Resource.Error -> {}
                else -> {}
            }
            LazyColumn(modifier = modifier) {
            }
        }
        Log.e("selected", "$selected")
        ButtonItem(
            text = stringResource(id = R.string.next),
            onClick = {
//                val selectedString = selected.joinToString { "," }
                navController.navigate(
                    Screen.AddTask.createRoute(category = category, goal = goal, tasks = selected)
                ) },
            modifier = modifier
        )
    }

}