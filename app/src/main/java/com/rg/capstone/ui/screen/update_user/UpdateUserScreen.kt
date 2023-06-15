package com.rg.capstone.ui.screen.update_user

import android.os.Build
import android.util.Log
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.rg.capstone.R
import com.rg.capstone.network.Resource
import com.rg.capstone.ui.component.ButtonItem
import com.rg.capstone.ui.component.TextFieldItem
import com.rg.capstone.ui.component.TitleScreenItem
import com.rg.capstone.ui.navigation.Screen
import com.rg.capstone.ui.theme.SpaceLarge
import com.rg.capstone.ui.theme.SpaceMedium

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateUserScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateUp: () -> Unit,
    viewModel: UpdateUserViewModel = hiltViewModel()
) {
    val calendarState = rememberSheetState()
    val updateState by viewModel.updateState.collectAsState()
    val userToken by viewModel.userToken.collectAsState()

    CalendarDialog(
        state = calendarState,
        selection = CalendarSelection.Date { date ->
            viewModel.setDateText(date.toString())
            Log.d("SelectedDate", "${date.toString()}")
        },
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true,
        )
    )
    Column(modifier = modifier.padding(SpaceLarge)) {
        TitleScreenItem(title = R.string.account, onBackClick = navigateUp)
        Spacer(modifier = modifier.height(SpaceMedium))
        TextFieldItem(
            title = stringResource(id = R.string.height),
            onValueChanged = { viewModel.setHeightText(it) },
            keyboardType = KeyboardType.Number,
            hintText = stringResource(id = R.string.fill_your_height),
            value = viewModel.heightText.value
        )
        Spacer(modifier = modifier.height(SpaceMedium))
        TextFieldItem(
            title = stringResource(id = R.string.weight),
            onValueChanged = { viewModel.setWeightText(it) },
            keyboardType = KeyboardType.Number,
            hintText = stringResource(id = R.string.fill_your_weight),
            value = viewModel.weightText.value
        )
        Spacer(modifier = modifier.height(SpaceMedium))
        Text(
            text = stringResource(id = R.string.date_of_birth),
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 14.sp
            )
        )
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { calendarState.show() }) {
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = stringResource(R.string.pick_date),
                    tint = Color.Black,
                    modifier = modifier,
                )
            }
            if (viewModel.dateText.value.isNotEmpty()) {
                Text(
                    text = viewModel.dateText.value,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500
                    )
                )
            } else {
                Text(
                    text = stringResource(id = R.string.pick_your_date_of_birth),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 16.sp,
                        color = Color.LightGray,
                        fontWeight = FontWeight.W400
                    )
                )
            }
        }
        Spacer(modifier = modifier.height(SpaceMedium))
        Text(
            text = stringResource(id = R.string.gender),
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 14.sp
            )
        )

        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = viewModel.selectedGender.value == "Female",
                onCheckedChange = { checked ->
                    if (checked) {
                        viewModel.setSelectedGender("F")
                    }
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = colorResource(id = R.color.dark_blue),
                    uncheckedColor = Color.Transparent
                ),
                modifier = Modifier.padding(end = SpaceMedium)
            )
            Text(
                text = stringResource(id = R.string.female),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 16.sp,
                    color = if (viewModel.selectedGender.value == "F") Color.Black else Color.LightGray,
                    fontWeight = if (viewModel.selectedGender.value == "F") FontWeight.W500 else FontWeight.W400
                ),
                modifier = Modifier.clickable {
                    viewModel.setSelectedGender("F")
                }
            )
            Spacer(modifier = modifier.width(SpaceLarge))
            Checkbox(
                checked = viewModel.selectedGender.value == "Male",
                onCheckedChange = { checked ->
                    if (checked) {
                        viewModel.setSelectedGender("M")
                    }
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = colorResource(id = R.color.dark_blue),
                    uncheckedColor = Color.Transparent
                ),
                modifier = Modifier.padding(end = SpaceMedium)
            )
            Text(
                text = stringResource(id = R.string.male),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 16.sp,
                    color = if (viewModel.selectedGender.value == "M") Color.Black else Color.LightGray,
                    fontWeight = if (viewModel.selectedGender.value == "M") FontWeight.W500 else FontWeight.W400
                ),
                modifier = Modifier.clickable {
                    viewModel.setSelectedGender("M")
                }
            )
        }
        Spacer(modifier = modifier.height(SpaceLarge))
        ButtonItem(
            text = stringResource(R.string.save),
            onClick = {
                userToken?.let {
                    viewModel.updateUser(
                        token = it,
                        date = viewModel.dateText.value,
                        gender = viewModel.selectedGender.value,
                        height = viewModel.heightText.value.toInt(),
                        weight = viewModel.weightText.value.toInt()
                    )
                }
            },
            enabled = updateState !is Resource.Loading
        )
        when(val resource = updateState) {
            is Resource.Loading -> {
                CircularProgressIndicator(modifier = modifier.align(Alignment.CenterHorizontally))
            }
            is Resource.Success -> {
                LaunchedEffect(Unit) {
                    navController.navigateUp()
                }
            }
            is Resource.Error -> {
                Text(
                    text = "Update Failed: ${resource.message}",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else -> {}
        }
    }
}
