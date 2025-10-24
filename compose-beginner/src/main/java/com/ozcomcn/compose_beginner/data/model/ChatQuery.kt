package com.ozcomcn.compose_beginner.data.model

data class ChatQuery(
    val user: String,
    val query: String,
    val conversation_id: String = "",
    val inputs: Inputs = Inputs(),
    val response_mode: String = "blocking"
)