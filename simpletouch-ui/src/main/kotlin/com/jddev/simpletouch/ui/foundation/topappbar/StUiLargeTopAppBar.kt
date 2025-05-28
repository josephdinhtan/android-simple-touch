package com.jddev.simpletouch.ui.foundation.topappbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StUiLargeTopAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = stUiLargeTopAppbarScrollBehavior(),
    title: String,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.background,
        scrolledContainerColor = MaterialTheme.colorScheme.surfaceContainer
    ),
    actions: @Composable RowScope.() -> Unit = {},
    onBack: (() -> Unit)? = null,
) {
    CustomizedLargeTopAppBar(
        title = title,
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = onBack?.let { { NavigateBack(onBack) } } ?: {},
        colors = defaultTopAppBarColors().copy(
            containerColor = colors.containerColor,
            scrolledContainerColor = colors.scrolledContainerColor,
            titleContentColor = colors.titleContentColor,
            navigationIconContentColor = colors.navigationIconContentColor,
            actionIconContentColor = colors.actionIconContentColor,
        ),
        actions = actions,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScrollContentTest(
    innerPadding: PaddingValues,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    val range = 1..100
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(range.count()) { index ->
            Text(text = "- List item number ${index + 1}")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        val scrollBehavior = stUiLargeTopAppbarScrollBehavior()
        Scaffold(
            topBar = {
                StUiLargeTopAppBar(
                    title = "Centered Top App Bar",
                    actions = {
                        IconButton(onClick = { /* do something */ }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior,
                    onBack = {},
                )
            }
        ) { innerPadding ->
            ScrollContentTest(innerPadding, scrollBehavior)
        }
    }
}
