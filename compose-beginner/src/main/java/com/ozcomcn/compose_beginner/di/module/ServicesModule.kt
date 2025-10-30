package com.ozcomcn.compose_beginner.di.module

import com.ozcomcn.compose_beginner.data.ChatRepository
import com.ozcomcn.compose_beginner.data.services.ApiService
import com.ozcomcn.compose_beginner.data.services.ChatService
import com.ozcomcn.compose_beginner.data.source.ChatRemoteDataSource
import com.ozcomcn.compose_beginner.di.qualifier.DebugAppKeyQualifier
import com.ozcomcn.compose_beginner.di.qualifier.DebugUserQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {

    @DebugAppKeyQualifier
    @Provides
    fun provideDebugAppKey(): String {
        return "app-cBVVmuqndG7qQgM6vs3r8gUo"
    }

    @DebugUserQualifier
    @Provides
    fun provideDebugUser(): String {
        return "user-test"
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideChatService(retrofit: Retrofit): ChatService {
        return retrofit.create(ChatService::class.java)
    }

}


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideChatRepository(
        @DebugAppKeyQualifier appKey: String,
        chatService: ChatService
    ): ChatRepository {
        return ChatRepository(ChatRemoteDataSource(appKey, chatService))
    }

}