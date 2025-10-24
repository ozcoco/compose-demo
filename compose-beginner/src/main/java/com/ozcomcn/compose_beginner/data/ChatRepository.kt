package com.ozcomcn.compose_beginner.data

import com.ozcomcn.compose_beginner.data.model.ChatMessage
import com.ozcomcn.compose_beginner.data.model.ChatQuery
import com.ozcomcn.compose_beginner.data.model.Resource
import com.ozcomcn.compose_beginner.data.source.ChatRemoteDataSource
import kotlinx.coroutines.flow.Flow

class ChatRepository(
    private val chatRemoteDataSource: ChatRemoteDataSource
) {
    fun chatMessage(shatSend: ChatQuery): Flow<Resource<ChatMessage>> =
        chatRemoteDataSource.chatMessage(shatSend)
}