package com.rg.capstone.ui.component

import android.widget.ProgressBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rg.capstone.R
import com.rg.capstone.ui.theme.CapstoneTheme

@Composable
fun ProgressItem(
    color: Int,
    barColor: Int,
    image: Int,
    contentDesc: Int,
    title: String,
    percentage: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(color = Color.Transparent)
            .size(height = 158.dp, width = 123.dp),
    ) {
        Box(
            modifier = modifier
                .size(height = 115.dp, width = 113.dp)
                .align(Alignment.BottomStart)
                .background(color = colorResource(R.color.blue))
        ) {
            Row(
                modifier = modifier
                    .padding(horizontal = 7.dp, vertical = 20.dp)
                    .align(Alignment.BottomStart),
            ) {
                CircularProgressIndicator(
                    modifier = modifier
                        .size(40.dp),
                    color = colorResource(barColor)
                )
                Column(
                    modifier = modifier
                ) {
                    Text(text = title)
                    Text(text = percentage)
                }
            }
        }
        Image(
            painter = painterResource(id = image),
            contentDescription = stringResource(contentDesc),
            modifier = modifier
                .align(Alignment.TopEnd)
                .size(88.dp)
        )

    }
}

@Preview(showBackground = false)
@Composable
fun ProgressItemPreview() {
    CapstoneTheme {
        ProgressItem(
            image = R.drawable.health_home,
            contentDesc = R.string.content_desc_health,
            color = R.color.blue,
            barColor =  R.color.dark_blue,
            title = "Health",
            percentage = "68%"
        )
    }
}