package com.jddev.simpletouch.ui.uicatalog.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.customization.settingsui.group.StSettingsGroup
import com.jddev.simpletouch.ui.foundation.StUiScaffold
import com.jddev.simpletouch.ui.foundation.StUiSwitch
import com.jddev.simpletouch.ui.foundation.topappbar.stUiLargeTopAppbarScrollBehavior
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicButtonsScreen(
    onBack: () -> Unit,
) {
    var switchState by remember { mutableStateOf(false) }
    StUiScaffold(
        title = "Buttons",
        onBack = onBack
    ) {
        StSettingsUi(
            modifier = Modifier.padding(it),
            scrollBehavior = stUiLargeTopAppbarScrollBehavior()
        ) {
            StSettingsGroup {
                ButtonListItem(
                    title = "Fill button",
                    content = {
                        Button(content = { Text("Filled") }, onClick = {})
                    }
                )
                ButtonListItem(
                    title = "Outline button",
                    content = {
                        OutlinedButton(content = { Text("Outlined") }, onClick = {})
                    }
                )
                ButtonListItem(
                    title = "Switch",
                    content = {
                        StUiSwitch(
                            checked = switchState,
                            onCheckedChange = { switchState = it })
                    }
                )
            }
        }
    }
}

@Composable
private fun ButtonListItem(
    title: String,
    content: @Composable () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title, style = MaterialTheme.typography.titleLarge)
        content()
    }
}

@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        BasicButtonsScreen(onBack = {})
    }
}