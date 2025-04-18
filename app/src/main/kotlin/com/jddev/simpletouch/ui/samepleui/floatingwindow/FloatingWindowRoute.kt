package com.jddev.simpletouch.ui.samepleui.floatingwindow

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FloatingWindowRoute(
    bubbleMessengerViewModel: FloatingWindowViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val isFloatingViewEnabled = bubbleMessengerViewModel.isFloatingViewEnabled.collectAsState()
    val isServiceRunning = bubbleMessengerViewModel.isServiceRunning.collectAsState()

    val localContext = LocalContext.current.applicationContext
    var hasOverlayPermission by remember { mutableStateOf(Settings.canDrawOverlays(localContext)) }
    val overlayPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        hasOverlayPermission = Settings.canDrawOverlays(localContext)
        if (Settings.canDrawOverlays(localContext)) {
            Toast.makeText(localContext, "Overlay permission granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(localContext, "Overlay permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        if (!Settings.canDrawOverlays(localContext)) {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:${localContext.packageName}")
            )
            overlayPermissionLauncher.launch(intent)
        }
    }

    FloatingWindowScreen(
        onBack = onBack,
        hasOverlayPermission = hasOverlayPermission,
        isShowBubble = isFloatingViewEnabled.value,
        isServiceRunning = isServiceRunning.value,
        onServiceStateChange = { isEnable ->
            bubbleMessengerViewModel.requestStartStopService(isEnable)
        },
        showBubbleEnableChange = { isEnable ->
            bubbleMessengerViewModel.floatingViewEnabledStateChanged(isEnable)
        },
    )
}