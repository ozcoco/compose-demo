package com.ozcomcn.compose_beginner.data.source

import com.ozcomcn.compose_beginner.data.model.ChatMessage
import com.ozcomcn.compose_beginner.data.model.ChatQuery
import com.ozcomcn.compose_beginner.data.model.Conversations
import com.ozcomcn.compose_beginner.data.model.Resource
import com.ozcomcn.compose_beginner.data.requestHandle
import com.ozcomcn.compose_beginner.data.services.ChatService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow


class ChatRemoteDataSource(
    private val appKey: String,
    private val chatService: ChatService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun chatMessage(query: ChatQuery): Flow<Resource<ChatMessage>> {
        return requestHandle(
            dispatcher = dispatcher
        ) {
            chatService.chatMessage(
                appKey = appKey,
                query = query
            )
        }
    }

    fun conversations(
        user: String,
        lastId: String? = null,
        limit: Int = 20,
        sortBy: String? = null,
    ): Flow<Resource<Conversations>> {
        return requestHandle(
            dispatcher = dispatcher
        ) {
            chatService.conversations(
                appKey = appKey,
                user = user,
                lastId = lastId,
                limit = limit,
                sortBy = sortBy,
            )
        }
    }
}

