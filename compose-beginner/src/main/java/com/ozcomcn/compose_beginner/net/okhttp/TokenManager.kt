package com.ozcomcn.compose_beginner.net.okhttp

import com.ozcomcn.compose_beginner.data.source.DataStoreManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class TokenManager(
    private val dataStore: DataStoreManager
) {
    private lateinit var cachedToken: String

    init {
        CoroutineScope(Dispatchers.IO).launch {
            cachedToken = getTokenAsync()
        }
    }

    fun getTokenSync(): String = cachedToken

    suspend fun getTokenAsync(): String = dataStore.read(HttpSettingKeys.API_TOKEN, "").first()
}