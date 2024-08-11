package com.example.suitmedia.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.suitmedia.data.UserPagingSource
import com.example.suitmedia.data.api.ApiService
import com.example.suitmedia.data.api.DataItem
import kotlinx.coroutines.flow.Flow

class UserRepository private constructor(private val apiService:ApiService){

    fun getAllUsers(): Flow<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = { UserPagingSource(apiService) }
        ).flow
    }

    companion object{
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService
        ) = UserRepository(apiService)
    }

}