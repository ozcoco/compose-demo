package com.ozcomcn.compose_beginner.data.services

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users/{id}")
    suspend fun getData(@Path("id") id: Int): String

}