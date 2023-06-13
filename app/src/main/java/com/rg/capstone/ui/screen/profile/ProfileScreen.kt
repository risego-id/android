package com.rg.capstone.ui.screen.profile

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rg.capstone.R
import com.rg.capstone.network.Resource
import com.rg.capstone.ui.component.ButtonItem
import com.rg.capstone.ui.component.ProfileMenu
import com.rg.capstone.ui.navigation.Screen
import com.rg.capstone.ui.screen.home.HomeContent
import com.rg.capstone.ui.screen.login.LoginViewModel
import com.rg.capstone.ui.theme.CapstoneTheme
import com.rg.capstone.ui.theme.SpaceLarge
import com.rg.capstone.ui.theme.SpaceMedium
import com.rg.capstone.ui.theme.SpaceSmall

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
    navController: NavController,
) {
    val logoutState by viewModel.logoutState.collectAsState()
    val userToken by viewModel.userToken.collectAsState()
    val userId by viewModel.userId.collectAsState()

    if (userToken != null) {
        if (userToken!!.isNotEmpty()) {

        }
    } else {
        navController.navigate(
            Screen.Login.route
        )
    }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(SpaceLarge)
    ) {
        Box(modifier = modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = stringResource(R.string.profile_desc),
                modifier = modifier
                    .size(200.dp)
                    .align(Alignment.Center)
            )
            IconButton(
                onClick = { /*TODO*/ },
                modifier = modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(id = R.string.edit_profile),
                    modifier = modifier.size(30.dp),
                )
            }
        }
//        Row(modifier = modifier) {
//            Text(text = )
//        }

        Text(
            text = stringResource(id = R.string.settings),
            style = MaterialTheme.typography.headlineLarge
        )
        ProfileMenu(
            title = R.string.language,
            image = R.drawable.language,
            onClick = { /*TODO*/ },
            contentDesc = R.string.language_content_desc,
        )
        Spacer(modifier = modifier.padding(SpaceSmall))
        ProfileMenu(
            title = R.string.set_app_permission,
            image = R.drawable.permission,
            onClick = { /*TODO*/ },
            contentDesc = R.string.set_app_permission_content_desc,
        )
        Spacer(modifier = modifier.padding(SpaceSmall))
        ProfileMenu(
            title = R.string.about_us,
            image = R.drawable.about_us,
            onClick = { /*TODO*/ },
            contentDesc = R.string.about_us_content_desc,
        )
        Spacer(modifier = modifier.padding(SpaceMedium))
        ButtonItem(
            text = stringResource(R.string.logout),
            color = R.color.dark_red,
            onClick = {
                      viewModel.logout(userToken!!)
            },
            enabled = logoutState !is Resource.Loading
        )

        when(val resource = logoutState) {
            is Resource.Loading -> {
                CircularProgressIndicator(modifier = modifier.align(Alignment.CenterHorizontally))
            }
            is Resource.Success -> {
                LaunchedEffect(Unit) {
                    navController.navigate(
                        Screen.Login.route
                    )
                    navController.popBackStack()
                }
            }
            is Resource.Error -> {
                Text(
                    text = "Logout Failed: ${resource.message}",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else -> {}
        }
    }
}

@Composable
fun ProfileContent() {

}

@Preview (showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ProfileContentPreview() {
    CapstoneTheme {
//        ProfileScreen()
    }
}