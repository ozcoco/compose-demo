package com.ozcomcn.compose_beginner.di.module

import android.app.Application
import com.ozcomcn.compose_beginner.data.source.DataStoreManager
import com.ozcomcn.compose_beginner.di.qualifier.DebugQualifier
import com.ozcomcn.compose_beginner.di.qualifier.ReleaseQualifier
import com.ozcomcn.compose_beginner.net.okhttp.AuthInterceptor
import com.ozcomcn.compose_beginner.net.okhttp.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HttpModule {

    @DebugQualifier
    @Singleton
    @Provides
    fun providesDebugBaseUrl(): String {
        return "http://192.168.31.234/"
    }

    @ReleaseQualifier
    @Singleton
    @Provides
    fun providesReleaseBaseUrl(): String {
        return "https://api.github.com/"
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        app: Application,
        dataStoreManager: DataStoreManager
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(AuthInterceptor(TokenManager(dataStoreManager)))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .apply {
                val cacheSize = 10 * 1024 * 1024L // 10 MiB
                val cache = Cache(app.cacheDir, cacheSize)
                cache(cache)
            }
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(@DebugQualifier baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
//            .addConverterFactory(MoshiConverterFactory.create())
//            .addConverterFactory(ProtoConverterFactory.create())
            .build()
    }

}