package com.ozcomcn.compose_beginner.data

import com.ozcomcn.compose_beginner.data.model.ChatReceiver
import com.ozcomcn.compose_beginner.data.model.ChatSend
import com.ozcomcn.compose_beginner.data.source.ChatRemoteDataSource

class ChatRepository(
    private val chatRemoteDataSource: ChatRemoteDataSource
) {

    suspend fun getChatMessage(shatSend: ChatSend): ChatReceiver =
        chatRemoteDataSource.getChatMessage(shatSend)
}