package com.example.demokotlinflow.demo2.repository

import androidx.paging.PagingSource
import com.example.demokotlinflow.demo2.base.BaseService
import com.example.demokotlinflow.demo2.base.ClsUserResponse

class UserListDataSource(private val baseService: BaseService) :
    PagingSource<Int, ClsUserResponse.Data.User>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ClsUserResponse.Data.User> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response: ClsUserResponse? =
                baseService.getBaseApi()?.getAllUser(nextPageNumber, 10)
            LoadResult.Page(
                data = response?.data?.users ?: emptyList(),
                prevKey = if (nextPageNumber > 0) nextPageNumber - 10 else null,
                nextKey =  if (response?.data?.has_more == true) nextPageNumber + 10 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}