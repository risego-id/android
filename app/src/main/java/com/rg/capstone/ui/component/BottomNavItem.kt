package com.rg.capstone.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import com.rg.capstone.R
import com.rg.capstone.ui.theme.SpaceSmall

@Composable
fun RowScope.BottomNavItem(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    contentDescription: String? = null,
    selected: Boolean = false,
    selectedColor: Color = colorResource(id = R.color.dark_blue),
    unselectedColor: Color = Color.LightGray,
    onClick: () -> Unit,
) {
    BottomNavItem (
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        selectedColor = selectedColor,
        unselectedColor = unselectedColor,
        icon = icon
    )
}