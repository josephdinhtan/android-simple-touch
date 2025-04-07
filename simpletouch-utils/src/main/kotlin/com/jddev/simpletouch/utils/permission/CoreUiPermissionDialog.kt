package com.jddev.simpletouch.utils.permission

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun StUiCheckAndShowPermissionRequest(
    requiredPermissions: List<String>,
    content: @Composable () -> Unit,
) {
    val multiplePermissionsState = rememberMultiplePermissionsState(
        requiredPermissions
        //getPermissionRequired(androidVersion)
    )
    Box {
        content()
        // If all permissions are granted, then show screen with the feature enabled
        if (!multiplePermissionsState.allPermissionsGranted) {
            LaunchedEffect(Unit) {
                multiplePermissionsState.launchMultiplePermissionRequest()
            }
        }
    }
}

private fun getTextToShowGivenPermissions(): String {
    return "Please grant all permissions for the app to function properly."
}
