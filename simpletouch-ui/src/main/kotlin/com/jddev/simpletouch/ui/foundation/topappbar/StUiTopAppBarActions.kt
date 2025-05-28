package com.jddev.simpletouch.ui.foundation.topappbar

import androidx.appcompat.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.FindInPage
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

/** Action that navigates back to last page. */
@Composable
fun NavigateBack(
    onBack: () -> Unit,
) {
    val contentDescription = stringResource(R.string.abc_action_bar_up_description)
    BackAction(contentDescription) { onBack() }
}

/** Action that collapses the search bar. */
@Composable
fun CollapseAction(onClick: () -> Unit) {
    val contentDescription = stringResource(R.string.abc_toolbar_collapse_description)
    BackAction(contentDescription, onClick)
}

@Composable
private fun BackAction(contentDescription: String, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier,
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
            contentDescription = contentDescription,
            modifier = Modifier,
        )
    }
}

/** Action that expends the search bar. */
@Composable
fun SearchAction(onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(
            imageVector = Icons.Outlined.FindInPage,
            contentDescription = stringResource(R.string.search_menu_title),
        )
    }
}

/** Action that clear the search query. */
@Composable
fun ClearAction(onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(
            imageVector = Icons.Outlined.Clear,
            contentDescription = stringResource(R.string.abc_searchview_description_clear),
        )
    }
}