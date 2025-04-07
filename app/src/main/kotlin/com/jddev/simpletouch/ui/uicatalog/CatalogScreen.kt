package com.jddev.simpletouch.ui.uicatalog

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.customization.settingsui.group.StSettingsGroup
import com.jddev.simpletouch.ui.customization.settingsui.navigation.StSettingsNavigateItem
import com.jddev.simpletouch.ui.foundation.StUiScaffold
import com.jddev.simpletouch.ui.foundation.topappbar.StUiTopAppBar
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(
    navigateToPager: () -> Unit,
    navigateToBottomNav: () -> Unit,
    navigateToBasicButton: () -> Unit,
    navigateToBasicDialog: () -> Unit,
    navigateToSettingsUi: () -> Unit,
    onBack: () -> Unit
) {
    StUiScaffold(
        topBar = {
            StUiTopAppBar(
                onBack = onBack,
                title = "UI catalog",
            )
        },
    ) {
        StSettingsUi(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            StSettingsGroup(header = "Basic components") {
                StSettingsNavigateItem(
                    title = "Button",
                    onClick = navigateToBasicButton
                )
                StSettingsNavigateItem(
                    title = "Popup dialog",
                    onClick = navigateToBasicDialog
                )
            }
            StSettingsGroup(header = "Navigation") {
                StSettingsNavigateItem(
                    title = "Bottom Navigation",
                    onClick = navigateToBottomNav
                )
                StSettingsNavigateItem(
                    title = "Horizontal Pager",
                    onClick = navigateToPager
                )
            }
            StSettingsGroup(header = "Settings UI") {
                StSettingsNavigateItem(
                    title = "Material Settings",
                    onClick = navigateToSettingsUi
                )
            }
        }
    }
}

@Preview(
    name = "Light theme", uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
private fun Preview() {
    StUiPreviewWrapper {
        CatalogScreen(
            navigateToPager = {},
            navigateToBottomNav = {},
            navigateToBasicButton = {},
            navigateToBasicDialog = {},
            navigateToSettingsUi = {},
            onBack = {}
        )
    }
}