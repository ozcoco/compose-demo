package com.ozcomcn.compose_beginner.data.source

import android.util.Log
import com.ozcomcn.compose_beginner.data.model.ChatMessage
import com.ozcomcn.compose_beginner.data.model.ChatQuery
import com.ozcomcn.compose_beginner.data.model.Conversations
import com.ozcomcn.compose_beginner.data.model.Messages
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
        Log.d(TAG, "--->chatMessage：query=$query")
        return requestHandle(
            dispatcher = dispatcher
        ) {
            chatService.chatMessage(
                authorization = "Bearer $appKey",
                query = query
            )
        }
    }

    fun conversations(
        user: String,
        lastId: String? = null,
        limit: Int = 20,
        sortBy: String = "created_at",
    ): Flow<Resource<Conversations>> {
        return requestHandle(
            dispatcher = dispatcher
        ) {
            Log.d(
                TAG,
                "--->conversations：user=$user, lastId=$lastId, limit=$limit, sortBy=$sortBy"
            )
            chatService.conversations(
                authorization = "Bearer $appKey",
                user = user,
                lastId = lastId,
                limit = limit,
                sortBy = sortBy,
            )
        }
    }

    fun messages(
        user: String,
        conversation_id: String,
        first_id: String? = null,
        limit: Int = 20,
    ): Flow<Resource<Messages>> {
        Log.d(
            TAG,
            "--->messages：user=$user, conversation_id=$conversation_id, first_id=$first_id, limit=$limit"
        )
        return requestHandle(
            dispatcher = dispatcher
        ) {
            chatService.messages(
                authorization = "Bearer $appKey",
                user = user,
                conversation_id = conversation_id,
                first_id = first_id,
                limit = limit,
            )
        }
    }


    companion object {
        private const val TAG = "ChatRemoteDataSource"
    }
}

