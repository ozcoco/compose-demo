package com.ozcomcn.compose_beginner.data.services

import com.ozcomcn.compose_beginner.data.model.ChatReceiver
import com.ozcomcn.compose_beginner.data.model.ChatSend
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChatService {

    @POST("v1/chat-messages")
    @Headers(
        "Content-Type:application/json",
        "Authorization:Bearer app-cBVVmuqndG7qQgM6vs3r8gUo"
    )
    suspend fun getChatMessage(@Body shatSend: ChatSend): ChatReceiver
}