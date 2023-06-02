package com.rg.capstone.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import com.rg.capstone.R
import com.rg.capstone.ui.theme.CapstoneTheme

@Composable
fun ButtonItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            // Set the background color to dark blue
            containerColor = colorResource(id = R.color.dark_blue),
            contentColor = Color.White
        ),
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(colorResource(id = R.color.dark_blue)),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium.copy(
                textAlign = TextAlign.Center
            ),
            modifier = modifier
                .weight(1f)
                .align(CenterVertically)
        )
    }
}

@Preview
@Composable
fun ButtonItemPreview() {
    CapstoneTheme {
        ButtonItem(
            text = "Log In",
            onClick = {}
        )
    }
}