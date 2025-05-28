package com.jddev.simpletouch.ui.uicatalog.bottomnavigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jddev.simpletouch.ui.foundation.topappbar.StUiTopAppBar
import com.jddev.simpletouch.ui.foundation.topappbar.stUiEnterAlwaysScrollBehavior

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationScreen(
    homeNavController: NavHostController = rememberNavController(),
    onBack: () -> Unit,
) {
    val scrollBehavior = stUiEnterAlwaysScrollBehavior()
    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            when (currentRoute) {
                null -> {
                    StUiTopAppBar(
                        scrollBehavior = scrollBehavior,
                        title = "Unexpected Error null",
                        onBack = onBack
                    )
                }

                else -> {
                    StUiTopAppBar(
                        title = BottomNavTab.entries.firstOrNull { it.route == currentRoute }?.label
                            ?: "Unexpected Error",
                        onBack = onBack
                    )
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = homeNavController)
        }
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NavHost(
                modifier = Modifier.weight(1f),
                navController = homeNavController,
                startDestination = BottomNavTab.Catalog.route,
            ) {
                composable(BottomNavTab.Catalog.route) { BottomNavContent(BottomNavTab.Catalog.label) }
                composable(BottomNavTab.Search.route) { BottomNavContent(BottomNavTab.Search.label) }
                composable(BottomNavTab.Favorites.route) { BottomNavContent(BottomNavTab.Favorites.label) }
                composable(BottomNavTab.Profile.route) { BottomNavContent(BottomNavTab.Profile.label) }
            }
            Text(
                text = "No label",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
            BottomNavigationBar(
                navController = homeNavController, windowInsets = null, alwaysShowLabel = false, hasLabel = false
            )
            HorizontalDivider()
            Text(
                text = "alwaysShowLabel = false",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
            BottomNavigationBar(
                navController = homeNavController, windowInsets = null, alwaysShowLabel = false
            )
            HorizontalDivider()
            Text(
                text = "Original size",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
            BottomNavigationBar(navController = homeNavController, windowInsets = null)
            HorizontalDivider()
            Text(
                text = "Adjusting with Android Navigation bar",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun BottomNavContent(title: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = title)
    }
}