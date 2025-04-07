package com.jddev.simpletouch.ui.customization.settingsui

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.jddev.simpletouch.ui.customization.settingsui.checkbox.StSettingsCheckBoxItem
import com.jddev.simpletouch.ui.customization.settingsui.group.StSettingsGroup
import com.jddev.simpletouch.ui.customization.settingsui.navigation.StSettingsNavigateItem
import com.jddev.simpletouch.ui.customization.settingsui.switch.StSettingsSwitchItem
import com.jddev.simpletouch.ui.foundation.topappbar.stUiPinnedScrollBehavior
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

/**
 * Standard function
 * suitable for displaying an entire screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StSettingsUi(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = stUiPinnedScrollBehavior(),
    stSettingsUiColors: StSettingsUiColors = StSettingsUiColors.Default,
    stSettingsUiDimension: StSettingsDimension = StSettingsDimension.Default,
    content: LazyListScope.() -> Unit
) {
    CompositionLocalProvider(
        LocalStSettingsUiColor provides stSettingsUiColors,
        LocalStSettingsUiDimension provides stSettingsUiDimension
    ) {
        val targetModifier = scrollBehavior?.let {
            modifier.nestedScroll(it.nestedScrollConnection)
        } ?: modifier
        LazyColumn(
            modifier = targetModifier,
            content = content,
        )
    }
}

/**
 * Base function which not includes scrollable
 * suitable for inserting halfway into a complex screen
 */
@Composable
fun StSettingsUiBase(
    modifier: Modifier = Modifier,
    stSettingsUiColors: StSettingsUiColors = StSettingsUiColors.Default,
    stSettingsUiDimension: StSettingsDimension = StSettingsDimension.Default,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalStSettingsUiColor provides stSettingsUiColors,
        LocalStSettingsUiDimension provides stSettingsUiDimension
    ) {
        Column(modifier = modifier) {
            content()
        }
    }
}

object StSettingsUi {
    val colors: StSettingsUiColors
        @Composable @ReadOnlyComposable get() = LocalStSettingsUiColor.current

    val dimension: StSettingsDimension
        @Composable @ReadOnlyComposable get() = LocalStSettingsUiDimension.current
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@StUiPreview
private fun Preview() {
    var checkBoxState by remember { mutableStateOf(true) }
    var toggleSwitchState by remember { mutableStateOf(true) }
    val subTitle = "Turn this feature on to do something"
    StUiPreviewWrapper {
        StSettingsUi {
            StSettingsGroup(
                header = "Group Title 1",
                content = {
                    StSettingsCheckBoxItem(
                        leadingImageVector = Icons.Default.Home,
                        title = "Check Box",
                        subTitle = subTitle,
                        checked = checkBoxState,
                        onCheckedChange = { checkBoxState = it },
                    )
                    StSettingsSwitchItem(
                        leadingImageVector = Icons.Default.Hub,
                        title = "Toggle Switch",
                        subTitle = subTitle,
                        checked = toggleSwitchState,
                        onCheckedChange = { toggleSwitchState = it },
                    )
                    StSettingsNavigateItem(
                        leadingImageVector = Icons.Default.Headphones,
                        title = "Navigate Item",
                        subTitle = subTitle,
                        onClick = {},
                    )
                },
            )
            item {
                HorizontalDivider()
            }
            StSettingsGroup(
                header = "Group Title 1",
                content = {
                    StSettingsCheckBoxItem(
                        leadingImageVector = Icons.Default.Home,
                        title = "Check Box",
                        subTitle = subTitle,
                        checked = checkBoxState,
                        onCheckedChange = { checkBoxState = it },
                    )
                    StSettingsSwitchItem(
                        leadingImageVector = Icons.Default.Hub,
                        title = "Toggle Switch",
                        subTitle = subTitle,
                        checked = toggleSwitchState,
                        onCheckedChange = { toggleSwitchState = it },
                    )
                    StSettingsNavigateItem(
                        leadingImageVector = Icons.Default.Headphones,
                        title = "Navigate Item",
                        subTitle = subTitle,
                        onClick = {},
                    )
                },
            )
        }
    }
}
