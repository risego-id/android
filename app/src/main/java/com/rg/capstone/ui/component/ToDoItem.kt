package com.rg.capstone.ui.component

import android.view.RoundedCorner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.Icon
import com.rg.capstone.R
import com.rg.capstone.ui.theme.CapstoneTheme

@Composable
fun ToDoItem(
    modifier: Modifier = Modifier,
    isDone: Boolean,
    image: Int,
    title: String,
) {
    if (isDone) {
        DoneItem(image = image, title = title)
    } else {
        NotDoneItem(image = image, title = title)
    }
}

@Composable
fun NotDoneItem(
    image: Int,
    title: String,
    modifier: Modifier = Modifier,
    description: String = "",
    backgroundColor: Int = R.color.blue,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(10.dp))
//            .shadow(1.dp)
    ){
        Row(
            modifier = modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = modifier
                .size(50.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = colorResource(id = backgroundColor))
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    modifier = modifier
                        .size(50.dp)
                )
            }
            Spacer(modifier = modifier.padding(5.dp))
            Column(modifier = modifier.weight(1f),) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.W600
                    )
                )
                if (description.isEmpty()) null else Text(text = description, style = MaterialTheme.typography.bodySmall)
            }
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.Black,
                modifier = modifier.size(40.dp),
            )
        }
    }
}

@Composable
fun DoneItem(
    image: Int,
    title: String,
    modifier: Modifier = Modifier,
    description: String = "",
    backgroundColor: Int = R.color.blue,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(id = R.color.light_gray))
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(10.dp))
//            .shadow(1.dp)
//            .alpha(0.8f)
    ){
        Row(
            modifier = modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = modifier
                .size(50.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = colorResource(id = backgroundColor))
                .alpha(alpha = 0.5f)
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    modifier = modifier
                        .size(50.dp)
                )
            }
            Spacer(modifier = modifier.padding(5.dp))
            Column(modifier = modifier.weight(1f),) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.W600,
                        color = Color.Gray
                    )
                )
                if (description.isEmpty()) null else Text(text = description, style = MaterialTheme.typography.bodySmall)
            }
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null,
                tint = Color.Gray,
                modifier = modifier.size(40.dp),
            )
        }
    }
}

@Preview
@Composable
fun DoneItemPreview() {
    CapstoneTheme {
        DoneItem(
            image = R.drawable.todo_health,
            title = "Walk 10k"
        )
    }
}

@Preview
@Composable
fun NotDoneItemPreview() {
    CapstoneTheme {
        NotDoneItem(
            image = R.drawable.todo_health,
            title = "Walk 10k"
        )
    }
}