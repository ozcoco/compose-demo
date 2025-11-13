package com.ozcomcn.compose_beginner.main.nav

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.ozcomcn.compose_beginner.base.nav.BaseNavKey
import com.ozcomcn.compose_beginner.main.vm.MainEffect
import com.ozcomcn.compose_beginner.main.vm.MainIntent
import com.ozcomcn.compose_beginner.main.vm.MainViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNav(
    modifier: Modifier = Modifier, vm: MainViewModel = hiltViewModel()
) {
    val navigator = vm.navigator
    val entryProviderBuilders = vm.entryProviderBuilders
    val state by vm.state.collectAsStateWithLifecycle()
    val isDarkTheme = state.isDarkTheme
    val title = navigator.currentDestination()?.let { (it as BaseNavKey).title } ?: "主页"
    val mainNavKeys = vm.navKeys.get().filter {
        it is BaseNavKey && it.isMainNavKey
    }.sortedBy {
        val navKey = it as BaseNavKey
        navKey.order
    }
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val onIntent: (MainIntent) -> Unit = { intent -> vm.onIntent(intent) }
    LaunchedEffect(vm) {
        // intent 处理
    }
    LaunchedEffect(vm) {
        // effect 处理
        vm.effect.collect { effect ->
            when (effect) {
                is MainEffect.NavigateTo -> {
                    val navKey = effect.destination as BaseNavKey
                    if (navKey.isMainNavKey) navigator.clearBackStack()
                    navigator.goTo(navKey)
                }
            }
        }
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.padding(end = 76.dp)
            ) {
                Text("菜单", modifier = Modifier.padding(8.dp))
                mainNavKeys.forEach { navKey ->
                    val itemTitle = (navKey as BaseNavKey).title
                    val isSelected = navigator.currentDestination() == navKey
                    NavigationDrawerItem(
                        icon = {
                            navKey.icon?.let {
                                Icon(
                                    imageVector = it, contentDescription = itemTitle
                                )
                            }
                        }, label = { Text(itemTitle) }, selected = isSelected, onClick = {
                            onIntent(MainIntent.NavigateTo(navKey))
                            scope.launch {
                                drawerState.close()
                            }
                        }, modifier = Modifier.padding(
                            start = 8.dp, end = 16.dp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(modifier = Modifier.padding(horizontal = 8.dp))
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = if (isDarkTheme) Icons.Default.LightMode else Icons.Default.DarkMode,
                            contentDescription = "主题模式"
                        )
                    }, label = { Text("主题模式") }, selected = false, onClick = {
                        //
                    }, modifier = Modifier.padding(
                        start = 8.dp, end = 16.dp, top = 8.dp
                    )
                )
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "设置"
                        )
                    }, label = { Text("设置") }, selected = false, onClick = {
                        //
                    }, modifier = Modifier.padding(
                        start = 8.dp, end = 16.dp, top = 8.dp
                    )
                )
            }
        },
        modifier = modifier.fillMaxSize(),
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ), title = {
                        Text(title)
                    }, navigationIcon = {
                        if (navigator.isBackable() && !(navigator.currentDestination() as BaseNavKey).isMainNavKey) {
                            IconButton(
                                onClick = { onIntent(MainIntent.NavigateBack()) }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                    contentDescription = "返回"
                                )
                            }
                        } else {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }) {
                                Icon(
                                    imageVector = Icons.Default.Menu, contentDescription = "菜单"
                                )
                            }
                        }
                    }, actions = {
                        var expanded by remember { mutableStateOf(false) }
                        IconButton(
                            onClick = {
                                expanded = !expanded
                            }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert, contentDescription = "更多"
                            )
                        }
                        DropdownMenu(
                            expanded = expanded, onDismissRequest = { expanded = false }) {
                            DropdownMenuItem(text = { Text("主题") }, leadingIcon = {
                                Icon(
                                    imageVector = if (isDarkTheme) Icons.Default.DarkMode else Icons.Default.LightMode,
                                    contentDescription = "主题"
                                )
                            }, onClick = {
                                expanded = false
                                onIntent(MainIntent.DarkThemeChange(!isDarkTheme))
                            })
                            DropdownMenuItem(text = { Text("设置") }, leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = "设置"
                                )
                            }, onClick = {
                                expanded = false
                            })
                        }
                    })
            },
        ) { paddingValues ->

            NavDisplay(
                modifier = Modifier.padding(paddingValues),
                transitionSpec = {
                    slideInHorizontally(initialOffsetX = { it }) togetherWith slideOutHorizontally(
                        targetOffsetX = { -it })
                },
                popTransitionSpec = {
                    slideInHorizontally(initialOffsetX = { -it }) togetherWith slideOutHorizontally(
                        targetOffsetX = { it })
                },
                predictivePopTransitionSpec = {
                    slideInHorizontally(initialOffsetX = { -it }) togetherWith slideOutHorizontally(
                        targetOffsetX = { it })
                },
                backStack = navigator.backStack,
                onBack = {
                    onIntent(MainIntent.NavigateBack())
                },
                entryProvider = entryProvider {
                    if (entryProviderBuilders.isNotEmpty()) entryProviderBuilders.forEach { builder -> this.builder() }
                })
        }
    }
}