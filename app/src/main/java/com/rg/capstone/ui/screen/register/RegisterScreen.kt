package com.rg.capstone.ui.screen.register

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rg.capstone.R
import com.rg.capstone.network.Resource
import com.rg.capstone.ui.component.ButtonItem
import com.rg.capstone.ui.component.TextFieldItem
import com.rg.capstone.ui.navigation.Screen
import com.rg.capstone.ui.theme.SpaceExtraLarge
import com.rg.capstone.ui.theme.SpaceMedium
import com.rg.capstone.ui.theme.SpaceSmall

@Composable
fun RegisterScreen(
    modifier: Modifier,
    viewModel: RegisterViewModel = hiltViewModel(),
    navController: NavController,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(SpaceExtraLarge)
            .verticalScroll(rememberScrollState()),
    ) {
        val registerState by viewModel.registerState.collectAsState()

        Image(
            painter = painterResource(id = R.drawable.register),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = modifier
                .size(150.dp)
        )
        Text(
            text = stringResource(id = R.string.register),
            style = MaterialTheme.typography.displayLarge
        )
        Spacer(modifier = modifier.height(SpaceMedium))
        TextFieldItem(
            value = viewModel.usernameText.value,
            title = stringResource(id = R.string.username),
            onValueChanged = { viewModel.setUsernameText(it) },
            error = viewModel.usernameError.value,
            hintText = stringResource(id = R.string.username_hint)
        )
        Spacer(modifier = modifier.height(SpaceSmall))
        TextFieldItem(
            value = viewModel.emailText.value,
            title = stringResource(id = R.string.email),
            onValueChanged = { viewModel.setEmailText(it) },
            keyboardType = KeyboardType.Email,
            error = viewModel.emailError.value,
            hintText = stringResource(id = R.string.email_hint)
        )
        Spacer(modifier = modifier.height(SpaceSmall))
        TextFieldItem(
            value = viewModel.passwordText.value,
            title = stringResource(id = R.string.password),
            onValueChanged = { viewModel.setPasswordText(it) },
            keyboardType = KeyboardType.Password,
            error = viewModel.passwordError.value,
            hintText = stringResource(id = R.string.password_hint),
            onPasswordToggleClick = {
                viewModel.setShowPassword(it)
            },
            isPasswordVisible = viewModel.showPassword.value
        )
        Spacer(modifier = modifier.height(SpaceSmall))
        TextFieldItem(
            value = viewModel.passwordConfirm.value,
            title = stringResource(id = R.string.password_confirm),
            onValueChanged = { viewModel.setPasswordConfirmText(it) },
            keyboardType = KeyboardType.Password,
            error = viewModel.passwordError.value,
            hintText = stringResource(id = R.string.password_hint),
            onPasswordToggleClick = {
                viewModel.setShowPasswordConfirm(it)
            },
            isPasswordVisible = viewModel.showPasswordConfirm.value
        )
        Spacer(modifier = modifier.height(SpaceMedium))
        ButtonItem(
            text = stringResource(id = R.string.register),
            onClick = {
                viewModel.userRegister(
                    name = viewModel.usernameText.value,
                    email = viewModel.emailText.value,
                    password = viewModel.passwordText.value,
                    password_confirm = viewModel.passwordConfirm.value
                )
            },
            enabled = registerState !is Resource.Loading
        )
        Spacer(modifier = modifier.height(SpaceExtraLarge))
        Text(
            text = buildAnnotatedString {
                append(stringResource(id = R.string.already_has_an_account))
                append(" ")
                val registerText = stringResource(id = R.string.login)
                withStyle(
                    style = SpanStyle(
                        color = colorResource(id = R.color.dark_blue)
                    )
                ) {
                    append(registerText)
                }
            },
            style = MaterialTheme.typography.bodySmall.copy(
                textAlign = TextAlign.Center
            ),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 14.dp)
                .clickable {
                    navController.navigate(
                        Screen.Login.route
                    )
                }
        )
        when (val resource = registerState) {
            is Resource.Loading -> {
                CircularProgressIndicator(modifier = modifier.align(Alignment.CenterHorizontally))
                Log.e(ContentValues.TAG, "Register Screen loading, $resource")
            }
            is Resource.Success -> {
                Log.e(ContentValues.TAG, "Register Screen Success $resource")
                LaunchedEffect(Unit) {
                    navController.navigate(
                        Screen.Home.route
                    )
                }
            }
            is Resource.Error -> {
                Log.e(ContentValues.TAG, "Error $resource")
                Text(
                    text = "Register Screen Failed: ${resource.message}",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else -> {}
        }
    }
}