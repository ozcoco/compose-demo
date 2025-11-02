package com.ozcomcn.compose_beginner.data.services

import com.ozcomcn.compose_beginner.data.model.ChatMessage
import com.ozcomcn.compose_beginner.data.model.ChatQuery
import com.ozcomcn.compose_beginner.data.model.Conversations
import com.ozcomcn.compose_beginner.data.model.Messages
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ChatService {

    @POST("v1/chat-messages")
    @Headers("Content-Type:application/json")
    suspend fun chatMessage(
        @Header("Authorization") authorization: String,
        @Body query: ChatQuery,
    ): ChatMessage


    @GET("v1/conversations")
//    @Headers("Content-Type:application/json")
    suspend fun conversations(
        @Header("Authorization") authorization: String,
        @Query("user") user: String,
        @Query("last_id") lastId: String? = null,
        @Query("limit") limit: Int = 20,
        @Query("sort_by") sortBy: String = "created_at",
    ): Conversations

    @GET("v1/messages")
//    @Headers("Content-Type:application/json")
    suspend fun messages(
        @Header("Authorization") authorization: String,
        @Query("user") user: String,
        @Query("conversation_id") conversation_id: String,
        @Query("first_id") first_id: String? = null,
        @Query("limit") limit: Int = 20,
    ): Messages


}