package com.ozcomcn.compose_beginner.data

import com.ozcomcn.compose_beginner.data.source.DataStoreManager

class AppSettingRepository(private val dataStore: DataStoreManager) {
    fun isDarkTheme() = dataStore.read(AppSettingKeys.IS_DARK_THEME, false)
    fun isUseLocalModel() = dataStore.read(AppSettingKeys.IS_USE_LOCAL_MODEL, false)
    suspend fun setDarkTheme(isDarkTheme: Boolean) {
        dataStore.save(AppSettingKeys.IS_DARK_THEME, isDarkTheme)
    }

    suspend fun setUseLocalModel(isUseLocalModel: Boolean) {
        dataStore.save(AppSettingKeys.IS_USE_LOCAL_MODEL, isUseLocalModel)
    }
}