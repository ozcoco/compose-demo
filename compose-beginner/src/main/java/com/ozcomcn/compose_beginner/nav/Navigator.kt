package com.ozcomcn.compose_beginner.nav

import androidx.compose.runtime.mutableStateListOf
import androidx.navigation3.runtime.NavKey
import dagger.hilt.android.scopes.ActivityRetainedScoped

@ActivityRetainedScoped
class Navigator(startDestination: NavKey) {

    val backStack by lazy { mutableStateListOf(startDestination) }

    fun goTo(destination: NavKey) {
        backStack.add(destination)
    }

    fun goBack() {
        backStack.removeLastOrNull()
    }
}