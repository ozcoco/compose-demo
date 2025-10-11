package com.ozcomcn.compose_beginner.data.model

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey

object AppSetting {
    val isDarkTheme: Preferences.Key<Boolean> = booleanPreferencesKey("is_dark_theme")
}