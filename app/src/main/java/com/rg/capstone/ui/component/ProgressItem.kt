package com.rg.capstone.ui.component

import android.widget.ProgressBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            .size(height = 158.dp, width = 123.dp)
    ) {
        Box(
            modifier = modifier
                .size(height = 115.dp, width = 113.dp)
                .align(Alignment.BottomStart)
                .clip(RoundedCornerShape(15.dp))
                .background(color = colorResource(color))
        ) {
            Row(
                modifier = modifier
                    .padding(bottom = 12.dp, start = 8.dp)
                    .align(Alignment.BottomStart),
            ) {
                CircularProgressIndicator(
                    modifier = modifier
                        .size(40.dp),
                    color = colorResource(barColor),
                    progress = .6f,
                    strokeWidth = 6.dp
                )
                Column(
                    modifier = modifier.padding(start = 5.dp)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W500
                        ),
                        color = colorResource(barColor)
                    )
                    Text(
                        text = percentage,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W600
                        ),
                        color = colorResource(barColor)
                    )
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
    Spacer(modifier = modifier.width(10.dp))
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
            title = "Finance",
            percentage = "68%"
        )
    }
}