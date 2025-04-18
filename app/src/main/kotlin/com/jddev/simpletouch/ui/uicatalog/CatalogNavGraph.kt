package com.jddev.simpletouch.ui.uicatalog

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.jddev.simpletouch.ui.settings.AppSettings
import com.jddev.simpletouch.ui.uicatalog.bottomnavigation.BottomNavScreen
import com.jddev.simpletouch.ui.uicatalog.button.BasicButtonsScreen
import com.jddev.simpletouch.ui.uicatalog.dialog.BasicDialogScreen
import com.jddev.simpletouch.ui.uicatalog.pager.HorizontalPagerDemo
import com.jddev.simpletouch.ui.uicatalog.settingsscreen.SettingsUiExampleScreen

fun NavGraphBuilder.uiCatalogNavGraph(
    route: String,
    navController: NavHostController,
    appSettings: AppSettings,
) {
    navigation(
        route = route,
        startDestination = "catalog_nav_home_route",
    ) {
        composable(
            route = "catalog_nav_home_route",
        ) {
            CatalogScreen(
                onBack = { navController.popBackStack() },
                navigateToPager = {
                    navController.navigate("catalog_nav_horizontal_pager_route")
                },
                navigateToBottomNav = {
                    navController.navigate("catalog_nav_bottom_nav_route")
                },
                navigateToBasicButton = {
                    navController.navigate("catalog_nav_basic_button_route")
                },
                navigateToBasicDialog = {
                    navController.navigate("catalog_nav_basic_dialog_route")
                },
                navigateToSettingsUi = {
                    val styleId = 1
                    navController.navigate("catalog_nav_settings_ui_route/$styleId")
                }
            )
        }
        composable(
            route = "catalog_nav_basic_button_route",
        ) {
            BasicButtonsScreen(
                onBack = { navController.popBackStack() },
            )
        }
        composable(
            route = "catalog_nav_basic_dialog_route",
        ) {
            BasicDialogScreen(
                onBack = { navController.popBackStack() },
            )
        }
        composable(
            route = "catalog_nav_bottom_nav_route",
        ) {
            BottomNavScreen(
                onBack = { navController.popBackStack() },
            )
        }
        composable(
            route = "catalog_nav_horizontal_pager_route",
        ) {
            HorizontalPagerDemo(
                onBack = { navController.popBackStack() },
            )
        }
        composable(
            route = "catalog_nav_settings_ui_route/{uiStyle}",
        ) {
            val stSettingsUiStyle = when (it.arguments?.getString("uiStyle")) {
                "0" -> ""
                else -> ""
            }
            SettingsUiExampleScreen(
                onBack = { navController.popBackStack() },
            )
        }
    }
}
