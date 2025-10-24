package com.ozcomcn.compose_beginner.data

import com.ozcomcn.compose_beginner.data.model.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun <T> requestHandle(
    dispatcher: CoroutineDispatcher = Dispatchers.IO, maxRetries: Int = 3, block: suspend () -> T
): Flow<Resource<T>> = flow {
    var retries = 0
    while (retries <= maxRetries) {
        emit(Resource.Loading)
        try {
            val data = block()
            emit(Resource.Success(data))
            return@flow
        } catch (e: Exception) {
            retries++
            if (retries > maxRetries) {
                emit(Resource.Error(e.message ?: "Unknown error"))
                return@flow
            } else {
                // 指数退避
                delay(1000L * retries)
            }
        }
    }
}.flowOn(dispatcher)
