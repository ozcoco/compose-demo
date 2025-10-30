package com.ozcomcn.compose_beginner.initializer

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import okhttp3.OkHttp

class NetworkInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        OkHttp.initialize(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> {
        Log.d("NetworkInitializer", "--->NetworkInitializer dependencies")
//        return listOf(WorkManagerInitializer::class.java)
        return emptyList()
    }
}