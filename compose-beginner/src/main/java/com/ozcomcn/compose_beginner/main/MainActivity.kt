package com.ozcomcn.compose_beginner.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.ozcomcn.compose_beginner.base.activity.BaseActivity
import com.ozcomcn.compose_beginner.main.nav.MainNav
import com.ozcomcn.compose_beginner.main.vm.MainViewModel
import com.ozcomcn.compose_beginner.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkTheme = vm.uiState.collectAsState().value.isDarkTheme
            AppTheme(
                dynamicColor = false,
                darkTheme = isDarkTheme
            ) {
                MainNav()
            }
        }
    }
}