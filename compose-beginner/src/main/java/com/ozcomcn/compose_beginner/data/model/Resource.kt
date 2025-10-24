package com.ozcomcn.compose_beginner.data.model

sealed interface Resource<out T> {
    object Loading : Resource<Nothing>
    data class Success<out T>(val data: T) : Resource<T>
    data class Error(val msg: String) : Resource<Nothing>
}