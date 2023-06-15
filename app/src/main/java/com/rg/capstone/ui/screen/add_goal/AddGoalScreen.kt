package com.rg.capstone.ui.screen.add_goal

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.navigation.NavController
import com.rg.capstone.R
import com.rg.capstone.ui.component.ButtonItem
import com.rg.capstone.ui.component.TitleScreenItem
import com.rg.capstone.ui.navigation.Screen
import com.rg.capstone.ui.theme.SpaceLarge
import com.rg.capstone.ui.theme.SpaceMedium


private val goalTypeList = listOf<String>(
    "More active",
    "Weight Loss",
    "Healthy Diet",
    "Muscle Growth"
)

@Composable
fun AddGoalScreen(
    category: String,
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateUp: () -> Unit
) {
    Column(modifier = modifier.padding(vertical = SpaceLarge)){
        TitleScreenItem(title = R.string.add_new_goal, onBackClick = navigateUp)
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            contentPadding = PaddingValues(12.dp),
            horizontalArrangement = Arrangement.spacedBy(SpaceMedium),
            verticalArrangement = Arrangement.spacedBy(SpaceMedium),
            modifier = modifier
//                .weight(.8f)
        ) {
            item {
                Box(
                    modifier = modifier
                        .clip(RoundedCornerShape(10.dp))
                        .border(
                            width = 2.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .height(150.dp)
                        .clickable {
//                            navController.navigate(
//                                Screen.
//                            )
                        }
                ) {
                    Text(
                        text = stringResource(R.string.more_active),
                        style = MaterialTheme.typography.labelLarge,
                        modifier = modifier.align(Alignment.Center)
                    )
                }
            }
            item {
                Box(
                    modifier = modifier
                        .clip(RoundedCornerShape(10.dp))
                        .border(
                            width = 2.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .height(150.dp)
//                        .clickable {
//                            navController.navigate(
//                                Screen.Recommendation.createRoute(category, )
//                            )
//                        }
                ) {
                    Text(
                        text = stringResource(R.string.weight_loss),
                        style = MaterialTheme.typography.labelLarge,
                        modifier = modifier.align(Alignment.Center)
                    )
                }
            }
            item {
                Box(
                    modifier = modifier
                        .clip(RoundedCornerShape(10.dp))
                        .border(
                            width = 2.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .height(150.dp)
                        .clickable {
                            navController.navigate(
                                Screen.Recommendation.createRoute(category, "Healthy Diet")
                            )
                        }
                ) {
                    Text(
                        text = stringResource(R.string.healthy_diet),
                        style = MaterialTheme.typography.labelLarge,
                        modifier = modifier.align(Alignment.Center)
                    )
                }
            }
            item {
                Box(
                    modifier = modifier
                        .clip(RoundedCornerShape(10.dp))
                        .border(
                            width = 2.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .height(150.dp)
                ) {
                    Text(
                        text = stringResource(R.string.muscle_growth),
                        style = MaterialTheme.typography.labelLarge,
                        modifier = modifier.align(Alignment.Center)
                    )
                }
            }
        }
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = SpaceMedium)
                .clip(RoundedCornerShape(10.dp))
                .border(
                    width = 2.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(10.dp)
                )
                .height(80.dp)
        ) {
            Text(
                text = stringResource(R.string.add_my_own),
                style = MaterialTheme.typography.labelLarge,
                modifier = modifier.align(Alignment.Center)
            )
        }
    }
}