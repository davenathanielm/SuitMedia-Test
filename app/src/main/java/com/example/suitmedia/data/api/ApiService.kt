package com.example.suitmedia.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/users")
    suspend fun getAllUsers(
        @Query("page") page:Int = 1,
        @Query("per_page") perPage:Int
    ): UserResponse
}