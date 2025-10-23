package com.ozcomcn.compose_beginner.data.model

data class ChatReceiver(
    val answer: String,
    val conversation_id: String,
    val created_at: Int,
    val event: String,
    val id: String,
    val message_id: String,
    val metadata: Metadata,
    val mode: String,
    val task_id: String
)