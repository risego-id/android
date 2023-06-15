package com.rg.capstone.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxkeppeker.sheets.core.models.base.SheetState
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.rg.capstone.R
import com.rg.capstone.ui.theme.CapstoneTheme
import com.rg.capstone.ui.theme.SpaceLarge
import com.rg.capstone.ui.theme.SpaceMedium
import com.rg.capstone.ui.theme.SpaceSmall

@Composable
fun AddTaskItem(
    modifier: Modifier = Modifier,
    number: Int,
    taskName: String = "",
    onValueTask1: (String) -> Unit,
    setSelectedType: (String) -> Unit,
    selectedType: String,
    startDateText: String,
    startCalendarState: SheetState,
    endDateText: String,
    endCalendarState: SheetState,
) {
    Box(
        modifier = modifier
            .border(width = 2.dp, color = Color.LightGray, shape = RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .fillMaxWidth()
            .padding(SpaceMedium)
    ) {
        Column(modifier = modifier) {
            TextFieldItem(
                title = stringResource(id = R.string.task_number, number),
                onValueChanged = onValueTask1,
                value = taskName
            )
            Spacer(modifier = modifier.height(SpaceSmall))
            Text(
                text = stringResource(R.string.type),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp
                )
            )
            Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = selectedType == "daily",
                    onCheckedChange = { checked ->
                        if (checked) {
                            setSelectedType
                        }
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = colorResource(id = R.color.dark_blue),
                        uncheckedColor = Color.Transparent
                    ),
//                    modifier = Modifier.padding(end = SpaceMedium)
                )
                Text(
                    text = stringResource(id = R.string.daily),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 16.sp,
                        color = if (selectedType == "D") Color.Black else Color.LightGray,
                        fontWeight = if (selectedType == "D") FontWeight.W500 else FontWeight.W400
                    ),
                    modifier = Modifier.clickable {
                        setSelectedType
                    }
                )
                Checkbox(
                    checked = selectedType == "daily",
                    onCheckedChange = { checked ->
                        if (checked) {
                            setSelectedType
                        }
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = colorResource(id = R.color.dark_blue),
                        uncheckedColor = Color.Transparent
                    ),
//                    modifier = Modifier.padding(end = SpaceMedium)
                )
                Text(
                    text = stringResource(id = R.string.daily),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 16.sp,
                        color = if (selectedType == "D") Color.Black else Color.LightGray,
                        fontWeight = if (selectedType == "D") FontWeight.W500 else FontWeight.W400
                    ),
                    modifier = Modifier.clickable {
                        setSelectedType
                    }
                )
                Checkbox(
                    checked = selectedType == "daily",
                    onCheckedChange = { checked ->
                        if (checked) {
                            setSelectedType
                        }
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = colorResource(id = R.color.dark_blue),
                        uncheckedColor = Color.Transparent
                    ),
//                    modifier = Modifier.padding(end = SpaceMedium)
                )
                Text(
                    text = stringResource(id = R.string.daily),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 16.sp,
                        color = if (selectedType == "D") Color.Black else Color.LightGray,
                        fontWeight = if (selectedType == "D") FontWeight.W500 else FontWeight.W400
                    ),
                    modifier = Modifier.clickable {
                        setSelectedType
                    }
                )
                Spacer(modifier = modifier.width(SpaceLarge))
                Checkbox(
                    checked = selectedType == "W",
                    onCheckedChange = { checked ->
                        if (checked) {
                            setSelectedType
                        }
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = colorResource(id = R.color.dark_blue),
                        uncheckedColor = Color.Transparent
                    ),
//                    modifier = Modifier.padding(end = SpaceMedium)
                )
                Text(
                    text = stringResource(id = R.string.weekly),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 16.sp,
                        color = if (selectedType == "W") Color.Black else Color.LightGray,
                        fontWeight = if (selectedType == "W") FontWeight.W500 else FontWeight.W400
                    ),
                    modifier = Modifier.clickable {
                        setSelectedType
                    }
                )
            }
            Spacer(modifier = modifier.height(SpaceSmall))
            Row(
                modifier = modifier,
//                horizontalArrangement = Arr
            ) {
                Column(modifier = modifier.weight(.3f)) {
                    Text(
                        text = stringResource(id = R.string.start),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 14.sp
                        )
                    )
                    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { startCalendarState.show() }) {
                            Icon(
                                imageVector = Icons.Default.CalendarMonth,
                                contentDescription = stringResource(R.string.pick_start_date),
                                tint = Color.Black,
                                modifier = modifier,
                            )
                        }
                        if (startDateText.isNotEmpty()) {
                            Text(
                                text = startDateText,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.W500
                                )
                            )
                        } else {
                            Text(
                                text = stringResource(id = R.string.pick_start_date),
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 16.sp,
                                    color = Color.LightGray,
                                    fontWeight = FontWeight.W400
                                )
                            )
                        }
                    }
                }
                Column(modifier = modifier.weight(.3f)) {
                    Text(
                        text = stringResource(id = R.string.end),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 14.sp
                        )
                    )
                    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { endCalendarState.show() }) {
                            Icon(
                                imageVector = Icons.Default.CalendarMonth,
                                contentDescription = stringResource(R.string.pick_end_date),
                                tint = Color.Black,
                                modifier = modifier,
                            )
                        }
                        if (endDateText.isNotEmpty()) {
                            Text(
                                text = endDateText,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.W500
                                )
                            )
                        } else {
                            Text(
                                text = stringResource(id = R.string.pick_end_date),
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 16.sp,
                                    color = Color.LightGray,
                                    fontWeight = FontWeight.W400
                                )
                            )
                        }
                    }
                }
            }
            Text(
                text = stringResource(id = R.string.cancel_task),
                style = MaterialTheme.typography.labelMedium.copy(
                    color = colorResource(id = R.color.dark_red),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                ),
                modifier = modifier.fillMaxWidth().padding(vertical = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun AddTaskItemPreview() {
    CapstoneTheme {
        AddTaskItem(
            number = 1,
            onValueTask1 = {},
            selectedType = "daily",
            setSelectedType = {},
            taskName = "a",
            startDateText = "",
            startCalendarState = rememberSheetState(),
            endDateText = "",
            endCalendarState = rememberSheetState()
        )
    }
}
