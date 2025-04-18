package com.jddev.simpletouch.ui.samepleui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.jddev.simpletouch.ui.samepleui.fitbit.FitbitSettingsScreen
import com.jddev.simpletouch.ui.samepleui.floatingwindow.FloatingWindowRoute
import com.jddev.simpletouch.ui.samepleui.intelligentcharging.IntelligentChargingRoute
import com.jddev.simpletouch.ui.samepleui.snakegame.SnakeGameRoute
import com.jddev.simpletouch.ui.settings.AppSettings

fun NavGraphBuilder.sampleUiNavGraph(
    route: String,
    navController: NavHostController,
    appSettings: AppSettings,
) {
    navigation(
        route = route,
        startDestination = "sampleui_nav_home_route",
    ) {
        composable(
            route = "sampleui_nav_home_route",
        ) {
            SampleUiScreen(
                navigateToBubbleMessenger = {
                    navController.navigate("sampleui_nav_bubble_messenger_route")
                },
                navigateToIntelligentCharging = {
                    navController.navigate("sampleui_nav_intelligent_charging_route")
                },
                navigateToSnakeGame = {
                    navController.navigate("sampleui_nav_snake_game_route")
                },
                navigateTo = { nextRoute ->
                    navController.navigate(nextRoute)
                },
                onBack = {
                    navController.popBackStack()
                },
            )
        }
        composable(
            route = "sampleui_nav_bubble_messenger_route",
            deepLinks = listOf(navDeepLink {
                uriPattern = "https://jddev.com/floating_window/"
            })
        ) {
            FloatingWindowRoute(
                onBack = {
                    navController.popBackStack()
                },
            )
        }
        composable(
            route = "sampleui_nav_intelligent_charging_route",
            deepLinks = listOf(navDeepLink {
                uriPattern =
                    "https://jddev.com/intelligent_charging/"
            })
        ) {
            IntelligentChargingRoute(
                onBack = { navController.popBackStack() },
            )
        }
        composable(
            route = "sampleui_nav_snake_game_route",
        ) {
            SnakeGameRoute(
                onBack = { navController.popBackStack() },
            )
        }
        composable(
            route = "fitbit_settings_ui",
        ) {
            FitbitSettingsScreen(
                onBack = { navController.popBackStack() },
            )
        }
    }
}
