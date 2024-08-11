package com.example.suitmedia.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.suitmedia.data.api.ApiService
import com.example.suitmedia.data.api.DataItem
import java.io.IOException

class UserPagingSource(
    private val  apiService: ApiService
):PagingSource<Int,DataItem>() {
    private companion object{
        const val INITIAL_PAGE_INDEX = 1
    }
    private val TAG = "UserPaging"

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            Log.d(TAG, "Loading page: ${params.key ?: INITIAL_PAGE_INDEX}")
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getAllUsers(position, params.loadSize)
            Log.d(TAG, "Data loaded successfully")

            val data = responseData.data?.filterNotNull().orEmpty()
            LoadResult.Page(
                data = data,
                prevKey = if(position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if(data.isEmpty()) null else position + 1,
            )
        }catch (e : IOException){
            Log.e(TAG, "IOException occurred: ${e.message}")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}