package com.ozcomcn.compose_beginner.di.module

import com.ozcomcn.compose_beginner.data.ChatRepository
import com.ozcomcn.compose_beginner.data.services.ApiService
import com.ozcomcn.compose_beginner.data.services.ChatService
import com.ozcomcn.compose_beginner.data.source.ChatRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {

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
    fun provideChatRepository(chatService: ChatService): ChatRepository {
        return ChatRepository(ChatRemoteDataSource(chatService))
    }

}