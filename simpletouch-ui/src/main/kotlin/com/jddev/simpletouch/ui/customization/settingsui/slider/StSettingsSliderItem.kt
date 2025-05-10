package com.jddev.simpletouch.ui.customization.settingsui.slider

import androidx.annotation.IntRange
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsItemBase
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@Composable
fun StSettingsSliderItem(
    modifier: Modifier = Modifier,
    title: String,
    value: Float,
    @IntRange(from = 0) steps: Int = 0,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    leadingImageVector: ImageVector? = null,
    leadingIconPainter: Painter? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    supportingContent: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    colors: SliderColors = SliderDefaults.colors(
        thumbColor = MaterialTheme.colorScheme.secondary,
        activeTrackColor = MaterialTheme.colorScheme.secondary,
        inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
        inactiveTickColor = Color.Transparent,
        activeTickColor = Color.Transparent
    ),
    onValueChange: ((Float) -> Unit),
    onValueChangeFinished: (() -> Unit)? = null,
) {
    StSettingsItemBase(
        modifier = modifier,
        title = title,
        leadingContent = leadingImageVector?.let {
            {
                Icon(
                    it,
                    "leading",
                    modifier = Modifier.alpha(if (enabled) 1f else StSettingsUi.colors.disableAlpha),
                )
            }
        } ?: leadingIconPainter?.let {
            {
                Icon(
                    it,
                    "leading",
                    modifier = Modifier.alpha(if (enabled) 1f else StSettingsUi.colors.disableAlpha),
                )
            }
        } ?: leadingContent,
        supportingContent = {
            supportingContent ?: Slider(
                value = value,
                onValueChange = onValueChange,
                onValueChangeFinished = onValueChangeFinished,
                colors = colors,
                steps = steps,
                valueRange = valueRange,
            )
        },
        enabled = enabled,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@StUiPreview
private fun PreviewEnable() {
    StUiPreviewWrapper {
        var value by remember { mutableFloatStateOf(50f) }
        Text("Value: $value")
        StSettingsUi {
            item {
                StSettingsSliderItem(
                    leadingImageVector = Icons.Outlined.MusicNote,
                    title = "Media volume",
                    value = value,
                    steps = 99,
                    valueRange = 0f..100f,
                    onValueChange = { value = it },
                )
            }
        }
    }
}