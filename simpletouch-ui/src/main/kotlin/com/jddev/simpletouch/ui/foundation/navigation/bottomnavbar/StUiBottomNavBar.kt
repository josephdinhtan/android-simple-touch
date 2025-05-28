package com.jddev.simpletouch.ui.foundation.navigation.bottomnavbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

sealed class NavIcon {
    data class Vector(val icon: ImageVector) : NavIcon()
    data class Painter(val painter: androidx.compose.ui.graphics.painter.Painter) : NavIcon()
}

data class BottomNavItem(
    val route: String,
    val label: String?,
    val selectedIcon: NavIcon,
    val unselectedIcon: NavIcon,
)

@Composable
fun StUiBottomNavBar(
    navController: NavHostController,
    windowInsets: WindowInsets? = NavigationBarDefaults.windowInsets,
    items: List<BottomNavItem>,
    modifier: Modifier = Modifier,
    alwaysShowLabel: Boolean = true,
    onItemClick: ((BottomNavItem) -> Unit)? = null,
    itemIconSize: Dp = 24.dp,
    colors: NavigationBarItemColors = NavigationBarItemDefaults.colors(
        selectedIconColor = MaterialTheme.colorScheme.primary,
        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
        selectedTextColor = MaterialTheme.colorScheme.primary,
        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
        indicatorColor = Color.Transparent
    ),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalDivider(
            color = DividerDefaults.color.copy(alpha = 0.5f)
        )
        StUiNavigationBar(
            modifier = modifier,
            windowInsets = windowInsets,
            containerColor = Color.Transparent,
            tonalElevation = 0.dp
        ) {
            items.forEach { item ->
                val selected = currentDestination?.route == item.route
                StUiNavigationBarItem(
                    selected = selected,
                    onClick = {
                        if (!selected) {
                            onItemClick?.invoke(item)
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = {
                        RenderNavIcon(
                            icon = if (selected) item.selectedIcon else item.unselectedIcon,
                            size = itemIconSize,
                            contentDescription = item.label
                        )
                    },
                    label = item.label?.let {
                        {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.titleMedium.copy(fontSize = 14.sp)
                            )
                        }
                    },
                    alwaysShowLabel = alwaysShowLabel,
                    colors = colors
                )
            }
        }
    }
}

@Composable
private fun RenderNavIcon(icon: NavIcon, size: Dp, contentDescription: String?) {
    when (icon) {
        is NavIcon.Vector -> Icon(
            imageVector = icon.icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(size)
        )

        is NavIcon.Painter -> Icon(
            painter = icon.painter,
            contentDescription = contentDescription,
            modifier = Modifier.size(size)
        )
    }
}

@StUiPreview
@Composable
private fun Preview() {
    StUiPreviewWrapper {
        Scaffold(
            bottomBar = {
                StUiBottomNavBar(
                    navController = NavHostController(LocalContext.current),
                    items = listOf(
                        BottomNavItem(
                            route = "home",
                            label = "Home",
                            selectedIcon = NavIcon.Vector(Icons.Default.Home),
                            unselectedIcon = NavIcon.Vector(Icons.Outlined.Home)
                        ),
                        BottomNavItem(
                            route = "profile",
                            label = "Profile",
                            selectedIcon = NavIcon.Vector(Icons.Default.Person),
                            unselectedIcon = NavIcon.Vector(Icons.Outlined.Person)
                        ),
                        BottomNavItem(
                            route = "settings",
                            label = "Settings",
                            selectedIcon = NavIcon.Vector(Icons.Default.Person),
                            unselectedIcon = NavIcon.Vector(Icons.Outlined.Person)
                        )
                    )
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Text(text = "Hello", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}