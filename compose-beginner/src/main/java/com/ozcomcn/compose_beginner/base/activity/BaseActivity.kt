package com.ozcomcn.compose_beginner.base.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.ozcomcn.compose_beginner.ui.setEdgeToEdgeConfig

open class BaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setEdgeToEdgeConfig()
        super.onCreate(savedInstanceState)
    }
}