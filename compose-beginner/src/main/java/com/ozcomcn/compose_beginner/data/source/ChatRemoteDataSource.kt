package com.ozcomcn.compose_beginner.data.source

import com.ozcomcn.compose_beginner.data.model.ChatMessage
import com.ozcomcn.compose_beginner.data.model.ChatQuery
import com.ozcomcn.compose_beginner.data.model.Resource
import com.ozcomcn.compose_beginner.data.requestHandle
import com.ozcomcn.compose_beginner.data.services.ChatService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow


class ChatRemoteDataSource(
    private val chatService: ChatService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun chatMessage(query: ChatQuery): Flow<Resource<ChatMessage>> {
        return requestHandle(
            dispatcher = dispatcher
        ) {
            chatService.chatMessage(query)
        }
    }
}

