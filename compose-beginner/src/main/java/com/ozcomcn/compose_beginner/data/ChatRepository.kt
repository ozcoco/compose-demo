package com.ozcomcn.compose_beginner.data

import com.ozcomcn.compose_beginner.data.model.ChatMessage
import com.ozcomcn.compose_beginner.data.model.ChatQuery
import com.ozcomcn.compose_beginner.data.model.Conversations
import com.ozcomcn.compose_beginner.data.model.Messages
import com.ozcomcn.compose_beginner.data.model.Resource
import com.ozcomcn.compose_beginner.data.source.ChatRemoteDataSource
import kotlinx.coroutines.flow.Flow

class ChatRepository(
    private val chatRemoteDataSource: ChatRemoteDataSource
) {
    fun chatMessage(shatSend: ChatQuery): Flow<Resource<ChatMessage>> =
        chatRemoteDataSource.chatMessage(shatSend)

    fun conversations(
        user: String,
        lastId: String? = null,
        limit: Int = 20,
        sortBy: String = "created_at",
    ): Flow<Resource<Conversations>> =
        chatRemoteDataSource.conversations(user, lastId, limit, sortBy)

    fun messages(
        user: String,
        conversation_id: String,
        first_id: String? = null,
        limit: Int = 20,
    ): Flow<Resource<Messages>> =
        chatRemoteDataSource.messages(user, conversation_id, first_id, limit)
}