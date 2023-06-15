package com.rg.capstone.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material3.Text
import com.rg.capstone.R
import com.rg.capstone.ui.theme.CapstoneTheme
import com.rg.capstone.ui.theme.SpaceMedium

@Composable
fun ProfileMenu(
    title: Int,
    image: Int,
    onClick: () -> Unit,
    contentDesc: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
//            .shadow(1.dp)
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(15.dp))
            .background(Color.White)
            .clickable { onClick }
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = stringResource(id = contentDesc),
                modifier = modifier
                    .padding(SpaceMedium)
                    .size(30.dp)
            )
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp,
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
fun ProfileMenuPreview() {
    CapstoneTheme {
        ProfileMenu(R.string.language, R.drawable.language, onClick = {}, contentDesc = R.string.language_content_desc)
    }
}