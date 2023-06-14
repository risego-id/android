package com.rg.capstone.ui.screen.login

import android.content.ContentValues.TAG
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rg.capstone.R
import com.rg.capstone.network.Resource
import com.rg.capstone.ui.component.ButtonItem
import com.rg.capstone.ui.component.TextFieldItem
import com.rg.capstone.ui.navigation.Screen
import com.rg.capstone.ui.theme.CapstoneTheme
import com.rg.capstone.ui.theme.SpaceExtraLarge
import com.rg.capstone.ui.theme.SpaceMedium
import com.rg.capstone.ui.theme.SpaceSmall

@Composable
fun LoginScreen(
    modifier: Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController,
    ) {
    val loginState by viewModel.loginState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(SpaceExtraLarge)
            .verticalScroll(rememberScrollState()),
    ) {
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = modifier
                .size(200.dp)
        )
        Text(
            text = "LOG IN",
            style = MaterialTheme.typography.displayLarge
        )
        Spacer(modifier = modifier.height(SpaceMedium))
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
        Spacer(modifier = modifier.height(SpaceMedium))
        ButtonItem(
            text = stringResource(id = R.string.login),
            onClick = {
                    viewModel.userLogin(
                        email = viewModel.emailText.value,
                        viewModel.passwordText.value,
                    )

                },
            enabled = loginState !is Resource.Loading
        )
        Spacer(modifier = modifier.height(SpaceExtraLarge))
        Text(
            text = buildAnnotatedString {
                append(stringResource(id = R.string.dont_have_account_yet))
                append(" ")
                val registerText = stringResource(id = R.string.register)
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
                        Screen.Register.route
                    )
                }
        )
        when (val resource = loginState) {
            is Resource.Loading -> {
                CircularProgressIndicator(modifier = modifier.align(Alignment.CenterHorizontally))
                Log.e(TAG, "loading, $resource")
            }
            is Resource.Success -> {
                Log.e(TAG, "Success $resource")
                LaunchedEffect(Unit) {
                    navController.navigate(
                        Screen.Home.route
                    )
                }
            }
            is Resource.Error -> {
                Log.e(TAG, "Error $resource")
                Text(
                    text = "Login Failed: ${resource.message}",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else -> {}
        }
    }
}


@Composable
fun LoginContent(
    email: String,
    emailError: String,
    password: String,
    passwordError: String,
    isPasswordVisible: Boolean,
    onValueChangeEmail: () -> Unit,
    onValueChangePassword: () -> Unit,
    setShowPassword: () -> Unit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(SpaceExtraLarge),
    ) {
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = modifier
                .size(200.dp)
        )
        Text(
            text = "LOG IN",
            style = MaterialTheme.typography.displayLarge
        )
        Spacer(modifier = modifier.height(SpaceMedium))
        TextFieldItem(
            value = email,
            title = stringResource(id = R.string.email),
            onValueChanged = { onValueChangeEmail },
            keyboardType = KeyboardType.Email,
            error = emailError,
            hintText = stringResource(id = R.string.email_hint)
        )
        Spacer(modifier = modifier.height(SpaceSmall))
        TextFieldItem(
            value = password,
            title = stringResource(id = R.string.password),
            onValueChanged = { onValueChangePassword },
            keyboardType = KeyboardType.Password,
            error = passwordError,
            hintText = stringResource(id = R.string.password_hint),
            onPasswordToggleClick = {
                setShowPassword
            },
            isPasswordVisible = isPasswordVisible
        )
        Spacer(modifier = modifier.height(SpaceMedium))
        ButtonItem(
            text = stringResource(id = R.string.login),
            onClick = onClick
        )
        Spacer(modifier = modifier.height(SpaceExtraLarge))
        Text(
            text = buildAnnotatedString {
                append(stringResource(id = R.string.dont_have_account_yet))
                append(" ")
                val registerText = stringResource(id = R.string.register)
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
                .clickable { }
        )
//        when (val resource = login)
    }

}

@Preview(device = Devices.PIXEL_4, showBackground = true)
@Composable
fun LoginContentPreview() {
    CapstoneTheme {
        LoginContent(
            email = "",
            emailError = "",
            isPasswordVisible = false,
            onValueChangeEmail = {},
            onValueChangePassword = {},
            password = "",
            passwordError = "",
            setShowPassword = {},
            onClick = {}
        )
    }
}