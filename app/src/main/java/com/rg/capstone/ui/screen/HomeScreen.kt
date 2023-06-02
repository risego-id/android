package com.rg.capstone.ui.screen

import android.graphics.drawable.Icon
import android.graphics.fonts.FontStyle
import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material3.Icon
import com.rg.capstone.ui.component.ChipsItem
import com.rg.capstone.ui.theme.CapstoneTheme

@Composable
fun HomeScreen() {

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
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
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
            IconButton(onClick = onClick) {
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
            onClick = {}
        )
    }
}