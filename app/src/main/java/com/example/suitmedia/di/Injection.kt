package com.example.suitmedia.di

import android.content.Context
import com.example.suitmedia.data.api.ApiConfig
import com.example.suitmedia.data.repository.UserRepository

object Injection {

    fun provideUserRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }
}