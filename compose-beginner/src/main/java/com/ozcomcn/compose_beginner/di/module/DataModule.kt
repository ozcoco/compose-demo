package com.ozcomcn.compose_beginner.di.module

import android.app.Application
import com.ozcomcn.compose_beginner.data.AppSettingRepository
import com.ozcomcn.compose_beginner.data.source.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun providesDataStoreManager(app: Application): DataStoreManager {
        return DataStoreManager(app)
    }

    @Singleton
    @Provides
    fun providesAppSettingRepository(dataStoreManager: DataStoreManager): AppSettingRepository {
        return AppSettingRepository(dataStoreManager)
    }

}