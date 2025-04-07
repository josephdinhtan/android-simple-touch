package com.jddev.simpletouch.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import com.jddev.simpletouch.AppContainer
import com.jddev.simpletouch.ui.settings.theme.AppThemeMode
import com.jddev.simpletouch.ui.theme.StUiTheme

@Composable
fun CoreArchApp(appContainer: AppContainer) {

    val isDarkMode = appContainer.appSettings.appThemeMode.collectAsState()
    val isDarkTheme = when (isDarkMode.value) {
        AppThemeMode.DARK -> true
        AppThemeMode.LIGHT -> false
        AppThemeMode.SYSTEM -> isSystemInDarkTheme()
    }

    StUiTheme(
        isDarkTheme = isDarkTheme,
    ) {
        val navController = rememberNavController()
        CoreArchNavGraph(
            appContainer = appContainer,
            navController = navController,
        )
    }
}