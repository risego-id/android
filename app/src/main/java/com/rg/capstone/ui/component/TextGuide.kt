package com.rg.capstone.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rg.capstone.ui.theme.CapstoneTheme

@Composable
fun TextGuide(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()){
        Text(
            text = "Health-DL",
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = "Health-DM",
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = "Health-DS",
            style = MaterialTheme.typography.displaySmall
        )
        Divider(modifier = modifier.height(10.dp))
        Text(
            text = "Health-TL",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "Health-TM",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Health-TS",
            style = MaterialTheme.typography.titleSmall
        )
        Divider(modifier = modifier.height(10.dp))
        Text(
            text = "Health-HL",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "Health-HM",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Health-HS",
            style = MaterialTheme.typography.headlineSmall
        )
        Divider(modifier = modifier.height(10.dp))
        Text(
            text = "Health-BL",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Health-BM",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Health-BS",
            style = MaterialTheme.typography.bodySmall
        )
        Divider(modifier = modifier.height(10.dp))
        Text(
            text = "Health-LL",
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = "Health-LM",
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = "Health-LS",
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview(device = Devices.NEXUS_5, showBackground = true)
@Composable
fun TextGuidePreview() {
    CapstoneTheme {
        TextGuide()
    }
}