package com.ozcomcn.compose_beginner.data

import com.ozcomcn.compose_beginner.data.services.ApiService

class UserRepository(
    private val apiService: ApiService
) {

    suspend fun getUser(id: Int): Result<String> {
        return try {
            val response = apiService.getData(id)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}