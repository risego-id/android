package com.rg.capstone.ui.screen.goal_category

import android.nfc.Tag
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import com.rg.capstone.R
import com.rg.capstone.ui.component.RecommendationItem
import com.rg.capstone.ui.component.TitleScreenItem
import com.rg.capstone.ui.navigation.Screen
import com.rg.capstone.ui.theme.CapstoneTheme
import com.rg.capstone.ui.theme.SpaceMedium
import com.rg.capstone.ui.theme.SpaceSmall

@Composable
fun GoalCategoryScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(SpaceMedium)

    ) {
        val context = LocalContext.current

        Text(
            text = stringResource(R.string.goals),
            style = MaterialTheme.typography.displayMedium,
        )
        Spacer(modifier = modifier.height(SpaceSmall))
        RecommendationItem(
            color = R.color.light_blue,
            image = R.drawable.health_recommendation,
            contentDesc = R.string.health_recommendation,
            title = R.string.health,
            fontColor = R.color.dark_blue,
            onClick = {
                navController.navigate(
                    Screen.ListGoal.route
                )
            },
            modifier = modifier.clickable { navController.navigate(
                Screen.ListGoal.route
            ) }
        )
        Spacer(modifier = modifier.height(SpaceMedium))
        RecommendationItem(
            color = R.color.light_green,
            image = R.drawable.finance_recommendation,
            contentDesc = R.string.finance_recommendation,
            title = R.string.finance,
            fontColor = R.color.dark_green,
            onClick = {
                Toast.makeText(context, R.string.under_construction, Toast.LENGTH_SHORT).show()
            },
            modifier = modifier.clickable {
                Toast.makeText(context, R.string.under_construction, Toast.LENGTH_SHORT).show()
            }

        )
        Spacer(modifier = modifier.height(SpaceMedium))
        RecommendationItem(
            color = R.color.light_yellow,
            image = R.drawable.skills_recommendation,
            contentDesc = R.string.skills_recommendation,
            title = R.string.skill,
            fontColor = R.color.dark_yellow,
            onClick = {
                Toast.makeText(context, R.string.under_construction, Toast.LENGTH_SHORT).show()
            },
            modifier = modifier.clickable {
                Toast.makeText(context, R.string.under_construction, Toast.LENGTH_SHORT).show()
            }
        )
    }
}
@Preview (showBackground = true, device = Devices.PIXEL_4)
@Composable
fun GoalCategoryPreview() {
    CapstoneTheme {
        GoalCategoryScreen(navController = NavController(LocalContext.current))
    }
}