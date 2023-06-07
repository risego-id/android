package com.rg.capstone.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material3.Text
import com.rg.capstone.R
import com.rg.capstone.ui.theme.CapstoneTheme
import com.rg.capstone.ui.theme.SpaceMedium

@Composable
fun RecTileItem(
    isSelected: Boolean,
    title: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isSelected) {
                Image(
                    painter = painterResource(id = R.drawable.rec_selected),
                    contentDescription = stringResource(id = R.string.recommendation_is_being_canceled),
                    modifier = modifier
                        .padding(SpaceMedium)
                        .size(25.dp)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.rec_cancel),
                    contentDescription = stringResource(id = R.string.recommendation_is_selected),
                    modifier = modifier
                        .padding(SpaceMedium)
                        .size(25.dp)
                )
            }

            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 13.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.W500,

                ),
                modifier = modifier
            )

        }
    }
}

@Preview
@Composable
fun RecTileItemPreview() {
    CapstoneTheme {
        RecTileItem(isSelected = false, title = "Walking WALKING WALKING WALKING")
    }
}