package com.rg.capstone.ui.screen.login

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.rg.capstone.R
import com.rg.capstone.ui.component.ButtonItem
import com.rg.capstone.ui.component.TextFieldItem
import com.rg.capstone.ui.theme.CapstoneTheme
import com.rg.capstone.ui.theme.SpaceLarge
import com.rg.capstone.ui.theme.SpaceMedium
import com.rg.capstone.ui.theme.SpaceSmall

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(SpaceLarge),
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
            onClick = { /*TODO*/ }
        )
        Spacer(modifier = modifier.height(SpaceLarge))
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
                .clickable {  }
        )
    }
}

@Composable
fun LoginContent(
//    image: Int,
//    email: ,
//    password: String,
//    modifier: Modifier = Modifier,
) {

}

@Preview(device = Devices.NEXUS_5, showBackground = true)
@Composable
fun LoginContentPreview() {
    CapstoneTheme {
        LoginScreen()
    }
}