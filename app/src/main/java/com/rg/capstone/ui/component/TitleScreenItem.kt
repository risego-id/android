package com.rg.capstone.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material3.Icon
import com.rg.capstone.R
import com.rg.capstone.ui.theme.CapstoneTheme
import org.w3c.dom.Text

@Composable
fun TitleScreenItem(
    title: Int,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = modifier
                .size(40.dp)
        ){
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.Black,
                modifier = modifier,
            )
        }
        Spacer(modifier = modifier.padding(5.dp))
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.displaySmall,
            modifier = modifier.weight(1f)
        )
    }
}

@Preview(device = Devices.NEXUS_5, showBackground = true)
@Composable
fun TitleScreenItemPreview() {
    CapstoneTheme {
        TitleScreenItem(title = R.string.add_new_goal, onBackClick = {})
    }
}