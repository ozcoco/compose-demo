package com.ozcomcn.compose_beginner.net.okhttp

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey

object HttpSettingKeys {
    val API_TOKEN: Preferences.Key<String> = stringPreferencesKey("API_TOKEN")
}