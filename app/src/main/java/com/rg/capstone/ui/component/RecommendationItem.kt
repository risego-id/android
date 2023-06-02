package com.rg.capstone.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rg.capstone.R
import com.rg.capstone.ui.theme.CapstoneTheme

@Composable
fun RecommendationItem(
    color: Int,
    image: Int,
    contentDesc: Int,
    title: Int,
    fontColor: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .size(height = 180.dp, width = 0.dp)
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(end = 40.dp)
                .clip(RoundedCornerShape(20.dp))
                .align(Alignment.TopStart)
                .background(colorResource(id = color))
        ) {
            Text(
                text = stringResource(id = contentDesc),
                style = MaterialTheme.typography.labelLarge.copy(
                    color = colorResource(id = fontColor)
                ),
                modifier = modifier
                    .padding(20.dp)
            )
            Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.displayLarge.copy(
                    color = colorResource(id = fontColor),
                    fontWeight = FontWeight.Bold
                ),
                modifier = modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 20.dp, bottom = 12.dp),
            )
        }
        Image(
            painter = painterResource(id = image),
            contentDescription = stringResource(id = contentDesc),
            modifier = modifier
                .align(Alignment.BottomEnd)
                .size(150.dp)
        )
    }
}

@Preview
@Composable
fun RecommendationItemPreview() {
    CapstoneTheme {
        RecommendationItem(
            color = R.color.blue,
            image = R.drawable.health_recommendation,
            title = R.string.health_title,
            contentDesc = R.string.goals_health_description,
            fontColor = R.color.dark_blue
        )
    }
}