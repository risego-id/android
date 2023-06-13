package com.rg.capstone.ui.screen.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.wear.compose.material3.Icon
import com.rg.capstone.R
import com.rg.capstone.ui.component.ChipsItem
import com.rg.capstone.ui.component.ProgressItem
import com.rg.capstone.ui.navigation.Screen
import com.rg.capstone.ui.theme.CapstoneTheme
import com.rg.capstone.ui.theme.SpaceMedium

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    ) {
//    Column(modifier = modifier) {
//        Text(text = stringResource(id = R.string.home))
//    }
    val userToken by viewModel.userToken.collectAsState()
    Log.e(TAG, "homescreen $userToken")

//    if (userToken.isNullOrEmpty()){
//        navController.navigate(
//            Screen.Login.route
//        )
//    } else {
//        HomeContent(name = "Azalia", onNotificationClick = { /*TODO*/ })
//    }

    if (userToken != null) {
        if (userToken!!.isNotEmpty()) {
            HomeContent(name = "Azalia", onNotificationClick = { /*TODO*/ })
        }
    } else {
        navController.navigate(
            Screen.Login.route
        )
    }

}

private val goalTypeList = listOf<String>(
    "Today",
    "This Week",
    "This Month",
    "This Year"
)

@Composable
fun HomeContent(
    name: String,
    onNotificationClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(SpaceMedium)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            Column(modifier = modifier.weight(1f)) {
                Text(
                    text = "Hi, $name",
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = "How are you?",
                    style = MaterialTheme.typography.labelLarge
                )
            }
            IconButton(onClick = onNotificationClick) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = modifier,
                )
            }
        }
        Spacer(modifier = modifier.height(10.dp))
        Text(
            text = "Check This Out!",
            style = MaterialTheme.typography.displaySmall.copy(
                fontSize = 26.sp
            )
        )
        Spacer(modifier = modifier.height(10.dp))
        Text(
            text = "Overview",
            style = MaterialTheme.typography.displaySmall.copy(
                fontSize = 26.sp
            )
        )
        LazyRow(modifier = modifier) {
            item {
                ProgressItem(
                    color = R.color.light_blue,
                    barColor = R.color.dark_blue,
                    image = R.drawable.health_home,
                    contentDesc = R.string.health_overview,
                    title = stringResource(R.string.health),
                    percentage = "89%"
                )
            }

            item {
                ProgressItem(
                    color = R.color.light_pink,
                    barColor = R.color.dark_pink,
                    image = R.drawable.finance_home,
                    contentDesc = R.string.finance_overview,
                    title = stringResource(R.string.finance),
                    percentage = "89%"
                )
            }
            item {
                ProgressItem(
                    color = R.color.light_yellow,
                    barColor = R.color.dark_yellow,
                    image = R.drawable.skill_home,
                    contentDesc = R.string.skill_overview,
                    title = stringResource(R.string.skill),
                    percentage = "89%"
                )
            }
        }
        Text(
            text = "Goals",
            style = MaterialTheme.typography.displaySmall.copy(
                fontSize = 26.sp
            )
        )
        ChipsItem(list = goalTypeList, onCLick = { /*TODO*/ })
    }
}

@Preview(device = Devices.NEXUS_5, showBackground = true)
@Composable
fun HomeContentPreview() {
    CapstoneTheme {
        HomeContent(
            name = "James",
            onNotificationClick = {}
        )
    }
}