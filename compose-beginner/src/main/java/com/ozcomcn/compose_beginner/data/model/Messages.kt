package com.ozcomcn.compose_beginner.data.model

data class Messages(
    val data: List<Data>,
    val has_more: Boolean,
    val limit: Int
) {
    data class Data(
        val agent_thoughts: List<Any>,
        val answer: String,
        val conversation_id: String,
        val created_at: Int,
        val error: Any,
        val feedback: Any,
        val id: String,
        val inputs: Map<String, String>,
        val message_files: List<Any>,
        val parent_message_id: String,
        val query: String,
        val retriever_resources: List<Any>,
        val status: String
    )

}