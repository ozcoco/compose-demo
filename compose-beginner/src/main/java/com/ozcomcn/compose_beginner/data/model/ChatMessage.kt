package com.ozcomcn.compose_beginner.data.model

data class ChatMessage(
    val answer: String,
    val conversation_id: String,
    val created_at: Int,
    val event: String,
    val id: String,
    val message_id: String,
    val metadata: Metadata,
    val mode: String,
    val task_id: String
) {
    data class Metadata(
        val annotation_reply: Any,
        val retriever_resources: List<Any>,
        val usage: Usage
    )

    data class Usage(
        val completion_price: String,
        val completion_price_unit: String,
        val completion_tokens: Int,
        val completion_unit_price: String,
        val currency: String,
        val latency: Double,
        val prompt_price: String,
        val prompt_price_unit: String,
        val prompt_tokens: Int,
        val prompt_unit_price: String,
        val total_price: String,
        val total_tokens: Int
    )
}