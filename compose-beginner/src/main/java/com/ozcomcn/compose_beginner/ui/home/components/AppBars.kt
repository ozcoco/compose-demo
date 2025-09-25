package com.ozcomcn.compose_beginner.ui.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ozcomcn.compose_beginner.ui.home.HomeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(scrollBehavior: TopAppBarScrollBehavior) {
    LargeTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        scrollBehavior = scrollBehavior,
        title = {
            Text(text = "组件")
        })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarsScreen(
    modifier: Modifier = Modifier,
    vm: HomeViewModel = viewModel()
) {
    Box(
        modifier = modifier.fillMaxSize(),
        content = {
            Text(text = "AppBars")
        }
    )
}