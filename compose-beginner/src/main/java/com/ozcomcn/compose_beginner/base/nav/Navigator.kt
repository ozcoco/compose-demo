package com.ozcomcn.compose_beginner.base.nav

import androidx.compose.runtime.mutableStateListOf
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey

typealias EntryProviderScope = EntryProviderScope<NavKey>.() -> Unit

open class Navigator(val startDestination: NavKey) {

    val backStack by lazy { mutableStateListOf(startDestination) }

    fun goTo(destination: NavKey) {
        backStack.add(destination)
    }

    fun goBack() {
        backStack.removeLastOrNull()
    }

    fun currentDestination(): NavKey? {
        return backStack.lastOrNull()
    }

    fun isBackable(): Boolean {
        return backStack.size > 1
    }

    fun clearBackStack() {
        backStack.clear()
    }

}