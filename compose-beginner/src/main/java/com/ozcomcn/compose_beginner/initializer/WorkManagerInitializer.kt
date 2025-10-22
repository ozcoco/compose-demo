package com.ozcomcn.compose_beginner.initializer

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import androidx.work.Configuration
import androidx.work.WorkManager

class WorkManagerInitializer : Initializer<WorkManager> {
    override fun create(context: Context): WorkManager {
        if (!WorkManager.isInitialized()) {
            val configuration = Configuration.Builder().build()
            WorkManager.initialize(context, configuration)
        }
        return WorkManager.getInstance(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> {
        Log.d("WorkManagerInitializer", "--->WorkManager dependencies")
        return emptyList()
    }
}