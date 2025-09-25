package com.ozcomcn.compose_beginner

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.ozcomcn.compose_beginner.base.BaseActivity
import com.ozcomcn.compose_beginner.ui.home.HomeScreen
import com.ozcomcn.compose_beginner.ui.home.HomeViewModel
import com.ozcomcn.compose_beginner.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                HomeScreen()
            }
        }
    }
}

