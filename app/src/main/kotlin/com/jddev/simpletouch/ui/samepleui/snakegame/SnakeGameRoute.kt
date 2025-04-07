package com.jddev.simpletouch.ui.samepleui.snakegame

import androidx.compose.runtime.Composable
import com.jddev.simpletouch.ui.foundation.StUiScaffold

@Composable
fun SnakeGameRoute(
    onBack: () -> Unit,
) {
    StUiScaffold(
        title = "Snake Game",
        onBack = onBack
    ) {
        SnakeGameContent()
    }
}