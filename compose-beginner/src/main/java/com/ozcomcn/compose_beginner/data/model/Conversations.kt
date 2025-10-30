package com.ozcomcn.compose_beginner.data.model

data class Conversations(
    val data: List<Data>, val has_more: Boolean, val limit: Int
) {
    data class Data(
        val created_at: Int,
        val id: String,
        val inputs: Map<String, String>,
        val name: String,
        val status: String,
        val updated_at: Int
    )
}