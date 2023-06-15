package com.rg.capstone.ui.screen.add_task

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.rg.capstone.R
import com.rg.capstone.network.Resource
import com.rg.capstone.ui.component.ButtonItem
import com.rg.capstone.ui.component.TextFieldItem
import com.rg.capstone.ui.component.TitleScreenItem
import com.rg.capstone.ui.navigation.Screen
import com.rg.capstone.ui.theme.SpaceLarge
import com.rg.capstone.ui.theme.SpaceMedium
import com.rg.capstone.ui.theme.SpaceSmall

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateUp: () -> Unit,
    category: String,
    goal: String,
    tasks: List<String>,
    viewModel: AddTaskViewModel = hiltViewModel()
) {
    val startCalendarState = rememberSheetState()
    val endCalendarState = rememberSheetState()
    val addGoalState by viewModel.addGoalState.collectAsState()
    val addTaskState by viewModel.addTaskState.collectAsState()
    val userToken by viewModel.userToken.collectAsState()
    val context = LocalContext.current

    if (userToken != null) {
        if (userToken!!.isNotEmpty()) {

        }
    }

    CalendarDialog(
        state = startCalendarState,
        selection = CalendarSelection.Date { date ->
            viewModel.setStartDate(date.toString())
            Log.d("SelectedDate", "${date.toString()}")
        },
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true,
        )
    )

    CalendarDialog(
        state = endCalendarState,
        selection = CalendarSelection.Date { date ->
            viewModel.setEndDate(date.toString())
            Log.d("SelectedDate", "${date.toString()}")
        },
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true,
        )
    )

    Column(modifier = modifier.padding(horizontal = SpaceMedium, vertical = SpaceLarge)) {
        TitleScreenItem(title = R.string.add_tasks, onBackClick = navigateUp)
        LazyColumn(modifier = modifier.weight(.8f), contentPadding = PaddingValues(10.dp)) {
            items(tasks.size) {
                Box(
                    modifier = modifier
                        .border(
                            width = 2.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(SpaceMedium)
                ) {
                    Column(modifier = modifier) {
                        TextFieldItem(
                            title = stringResource(id = R.string.task_number, it + 1),
                            onValueChanged = { _a -> viewModel.setTaskText(tasks[it]) },
                            value = viewModel.taskText.value,
                        )
                        Spacer(modifier = modifier.height(SpaceMedium))
                        Text(
                            text = stringResource(R.string.type),
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W500
                            )
                        )
                        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(
                                text = stringResource(id = R.string.daily),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontSize = 14.sp,
                                    color = if (viewModel.selectedType.value == "D") Color.Black else Color.LightGray,
                                    fontWeight = if (viewModel.selectedType.value == "D") FontWeight.W500 else FontWeight.W400
                                ),
                                modifier = Modifier.clickable {
                                    viewModel.setSelectedType("D")
                                }
                            )
                            Text(
                                text = stringResource(id = R.string.weekly),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontSize = 14.sp,
                                    color = if (viewModel.selectedType.value == "W") Color.Black else Color.LightGray,
                                    fontWeight = if (viewModel.selectedType.value == "W") FontWeight.W500 else FontWeight.W400
                                ),
                                modifier = Modifier.clickable {
                                    viewModel.setSelectedType("W")
                                }
                            )
                            Text(
                                text = stringResource(id = R.string.monthly),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontSize = 14.sp,
                                    color = if (viewModel.selectedType.value == "M") Color.Black else Color.LightGray,
                                    fontWeight = if (viewModel.selectedType.value == "M") FontWeight.W500 else FontWeight.W400
                                ),
                                modifier = Modifier.clickable {
                                    viewModel.setSelectedType("M")
                                }
                            )
                            Text(
                                text = stringResource(id = R.string.yearly),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontSize = 14.sp,
                                    color = if (viewModel.selectedType.value == "Y") Color.Black else Color.LightGray,
                                    fontWeight = if (viewModel.selectedType.value == "Y") FontWeight.W500 else FontWeight.W400
                                ),
                                modifier = Modifier.clickable {
                                    viewModel.setSelectedType("Y")
                                }
                            )
                        }
                        Spacer(modifier = modifier.height(SpaceMedium))
                        Row(
                            modifier = modifier,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
//
//                                Row(
//                                    modifier = modifier,
//                                    verticalAlignment = Alignment.CenterVertically
//                                ) {
                                    IconButton(onClick = { startCalendarState.show() }) {
                                        Icon(
                                            imageVector = Icons.Default.CalendarMonth,
                                            contentDescription = stringResource(R.string.pick_start_date),
                                            tint = Color.Black,
                                            modifier = modifier,
                                        )
                                    }
                                    Column(modifier = modifier.weight(.3f)) {
                                        Text(
                                            text = stringResource(id = R.string.start),
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.W500
                                            )
                                        )
                                    if (viewModel.startDateText.value.isNotEmpty()) {
                                        Text(
                                            text = viewModel.startDateText.value,
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.W500
                                            )
                                        )
                                    } else {
                                        Text(
                                            text = stringResource(id = R.string.pick_start_date),
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                fontSize = 14.sp,
                                                color = Color.LightGray,
                                                fontWeight = FontWeight.W400
                                            )
                                        )
                                    }
                                }
//                            }

//                                Row(
//                                    modifier = modifier,
//                                    verticalAlignment = Alignment.CenterVertically
//                                ) {

                                    IconButton(onClick = { endCalendarState.show() }) {
                                        Icon(
                                            imageVector = Icons.Default.CalendarMonth,
                                            contentDescription = stringResource(R.string.pick_end_date),
                                            tint = Color.Black,
                                            modifier = modifier,
                                        )
                                    }
                                    Column(modifier = modifier.weight(.3f)) {
                                        Text(
                                            text = stringResource(id = R.string.end),
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.W500
                                            ),
                                        )
                                    if (viewModel.endDateText.value.isNotEmpty()) {
                                        Text(
                                            text = viewModel.endDateText.value,
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.W500
                                            )
                                        )
                                    } else {
                                        Text(
                                            text = stringResource(id = R.string.pick_end_date),
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                fontSize = 14.sp,
                                                color = Color.LightGray,
                                                fontWeight = FontWeight.W400
                                            )
                                        )
                                    }
//                                }
                            }
                        }
                        Divider(modifier = modifier.padding(SpaceSmall))
                        Text(
                            text = stringResource(id = R.string.cancel_task),
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = colorResource(id = R.color.dark_red),
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp
                            ),
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        )
                    }
                }
                Spacer(modifier = modifier.height(SpaceLarge))
                }
            }
        ButtonItem(
            text = stringResource(id = R.string.save),
            onClick = {
                userToken?.let {
                    viewModel.addGoal(
                        tkn = it,
                        title = goal,
                        category = category,
                    )
                }
            },
            enabled = addGoalState !is Resource.Loading
        )
        when (val resource = addGoalState) {
            is Resource.Loading -> {}
            is Resource.Success -> {
                userToken?.let {
                    viewModel.addTask(
                        token = it,
                        goal_id = resource.data.data.id,
                        title = viewModel.taskText.value,
                        is_done = 0,
                        task_type = viewModel.selectedType.value,
                        start_date = viewModel.startDateText.value,
                        end_date = viewModel.endDateText.value
                    )
                    Log.d("task", "task: $it, ${resource.data.data.id}, ${viewModel.taskText.value}, ${viewModel.selectedType.value}, ${viewModel.startDateText.value}, ${viewModel.endDateText.value}")
                }
                when (val res = addTaskState) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        LaunchedEffect(Unit) {
                            navController.navigate(
                                Screen.ListGoal.route
                            )
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(context, "add task ${res.message}", Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            }
            is Resource.Error -> {
                Toast.makeText(context, "Add goal ${resource.message}", Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }
}
