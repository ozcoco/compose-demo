package com.ozcomcn.compose_beginner.data.services

import com.ozcomcn.compose_beginner.data.model.ChatMessage
import com.ozcomcn.compose_beginner.data.model.ChatQuery
import com.ozcomcn.compose_beginner.data.model.Conversations
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
        appKey: String,
        @Body query: ChatQuery,
        @Header("Authorization") authorization: String = "Bearer $appKey",
    ): ChatMessage


    @GET("v1/conversations")
    @Headers("Content-Type:application/json")
    suspend fun conversations(
        appKey: String,
        @Query("user") user: String,
        @Query("last_id") lastId: String? = null,
        @Query("limit") limit: Int = 20,
        @Query("sort_by") sortBy: String? = null,
        @Header("Authorization") authorization: String = "Bearer $appKey",
    ): Conversations

}