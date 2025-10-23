package com.ozcomcn.compose_beginner.data.model

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