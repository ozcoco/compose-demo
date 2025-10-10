package com.ozcomcn.compose_beginner.base.nav

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey

open class BaseNavKey(
    val title: String,
    val desc: String = "",
    val imgUrl: String = "",
    val icon: ImageVector? = null,
    val order: Int = 0,
    val isMainNavKey: Boolean = false
) : NavKey