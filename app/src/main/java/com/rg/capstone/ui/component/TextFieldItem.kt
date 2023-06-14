package com.rg.capstone.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material3.ContentAlpha
import com.rg.capstone.R
import com.rg.capstone.ui.theme.CapstoneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldItem(
    title: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    value: String = "",
    hintText: String = "",
    textStyle: TextStyle = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp),
    maxLines: Int = 1,
    boxColor: Int = R.color.dark_blue,
    keyboardType: KeyboardType = KeyboardType.Text,
    error: String = "",
    isPasswordToggleDisplayed: Boolean = keyboardType == KeyboardType.Password,
    isPasswordVisible: Boolean = false,
    onPasswordToggleClick: (Boolean) -> Unit = {},
    leadingIcon: ImageVector? = null,
    singleLine: Boolean = true
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 14.sp
            )
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChanged,
            textStyle = textStyle,
            maxLines = maxLines,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            visualTransformation = if (!isPasswordVisible && isPasswordToggleDisplayed) {
                PasswordVisualTransformation()
            } else {
                   VisualTransformation.None
            },
            trailingIcon = if(isPasswordToggleDisplayed) {
                val icon: @Composable () -> Unit = {
                    IconButton(
                        onClick = {
                            onPasswordToggleClick(!isPasswordVisible)
                        },
                        modifier = modifier
                    ) {
                        Icon(
                            imageVector = if (isPasswordVisible) {
                                Icons.Filled.VisibilityOff
                            } else {
                                Icons.Filled.Visibility
                            },
                            tint = colorResource(id = R.color.dark_blue),
                            contentDescription = if (isPasswordVisible) {
                                stringResource(id = R.string.hide_password)
                            } else {
                                stringResource(id = R.string.show_password)
                            }
                        )
                    }
                }
                icon
            } else null,
            singleLine = singleLine,
            isError = error != "",
            placeholder = {
                Text(
                    text = hintText,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.Gray
                    )
                )
            },
            modifier = modifier
                .fillMaxWidth()
//                .clip(RoundedCornerShape(10.dp))
//                .border(width = 2.dp, color = colorResource(id = boxColor))
                .height(50.dp),
        )
        if (error.isNotEmpty()){
            Text(
                text = error,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.Red
                )
            )
        }
    }
}

@Preview
@Composable
fun TextFieldItemPreview() {
    CapstoneTheme {
        TextFieldItem(
            value = "",
            title = "name",
            onValueChanged = {},
            hintText = "Enter your name",
            error = "ppp"
        )
    }
}