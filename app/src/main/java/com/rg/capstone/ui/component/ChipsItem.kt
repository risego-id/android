package com.rg.capstone.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ChipBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Device
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rg.capstone.ui.theme.CapstoneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipsItem(
    list: List<String>,
    onCLick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier) {
        items(list.size) {
            AssistChip(
                onClick = onCLick,
                label = {
                    Text(
                        text = list[it],
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 12.sp,
                        )
                    )
                },
                border = AssistChipDefaults.assistChipBorder(
                    borderColor = Color.Transparent,
                    borderWidth = 0.dp
                ),
                modifier = modifier
            )
        }
    }
}

@Preview(device = Devices.NEXUS_5, showBackground = true)
@Composable
fun ChipsItemPreview() {
    CapstoneTheme {
        val list = listOf<String>(
            "Today",
            "This Week",
            "This Month",
            "This Year"
        )
        ChipsItem(list = list, onCLick = {})
    }
}