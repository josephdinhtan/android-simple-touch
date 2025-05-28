package com.jddev.simpletouch.ui.foundation.navigation.bottomnavbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StUiNavigationBar(
    modifier: Modifier = Modifier,
    containerColor: Color = NavigationBarDefaults.containerColor,
    contentColor: Color = MaterialTheme.colorScheme.contentColorFor(containerColor),
    tonalElevation: Dp = NavigationBarDefaults.Elevation,
    windowInsets: WindowInsets? = NavigationBarDefaults.windowInsets,
    navigationBarMinHeight: Dp = 64.dp,
    navigationBarItemHorizontalPadding: Dp = 8.dp,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        modifier = modifier
    ) {
        Row(
            modifier =
            Modifier.fillMaxWidth()
                .then(
                    if (windowInsets != null) Modifier.windowInsetsPadding(windowInsets)
                    else Modifier
                )
                .defaultMinSize(minHeight = navigationBarMinHeight)
                .selectableGroup(),
            horizontalArrangement = Arrangement.spacedBy(navigationBarItemHorizontalPadding),
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}