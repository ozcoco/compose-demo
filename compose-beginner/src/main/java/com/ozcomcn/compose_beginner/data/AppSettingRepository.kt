package com.ozcomcn.compose_beginner.data

import com.ozcomcn.compose_beginner.data.AppSettingKeys
import com.ozcomcn.compose_beginner.data.source.DataStoreManager

class AppSettingRepository(private val dataStore: DataStoreManager) {
    fun isDarkTheme() = dataStore.read(AppSettingKeys.IS_DARK_THEME, false)
    suspend fun setDarkTheme(isDarkTheme: Boolean) {
        dataStore.save(AppSettingKeys.IS_DARK_THEME, isDarkTheme)
    }
}