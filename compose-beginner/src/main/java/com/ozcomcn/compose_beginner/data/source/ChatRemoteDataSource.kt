package com.ozcomcn.compose_beginner.data.source

import com.ozcomcn.compose_beginner.data.model.ChatReceiver
import com.ozcomcn.compose_beginner.data.model.ChatSend
import com.ozcomcn.compose_beginner.data.services.ChatService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChatRemoteDataSource(
    private val chatService: ChatService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getChatMessage(shatSend: ChatSend): ChatReceiver =
        withContext(dispatcher) {
            chatService.getChatMessage(shatSend)
        }


}