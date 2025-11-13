package com.ozcomcn.compose_beginner.data

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey

object AppSettingKeys {
    val IS_DARK_THEME: Preferences.Key<Boolean> = booleanPreferencesKey("IS_DARK_THEME")
    val IS_USE_LOCAL_MODEL: Preferences.Key<Boolean> = booleanPreferencesKey("IS_USE_LOCAL_MODEL")
}