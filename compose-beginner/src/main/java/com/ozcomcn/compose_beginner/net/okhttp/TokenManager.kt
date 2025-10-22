package com.ozcomcn.compose_beginner.net.okhttp

import com.ozcomcn.compose_beginner.data.source.DataStoreManager
import jakarta.inject.Inject

class TokenManager @Inject constructor(
    private val dataStore: DataStoreManager
) {
    fun getToken(): String {
        return ""
    }
}