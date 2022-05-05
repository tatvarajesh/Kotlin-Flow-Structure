package com.example.demokotlinflow.demo2.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.demokotlinflow.demo2.base.BaseService
import com.example.demokotlinflow.demo2.repository.UserListDataSource

class UserViewModel(private val api: BaseService) : ViewModel() {

    val userList = Pager(
        config = PagingConfig(pageSize = 10, prefetchDistance = 2),
        pagingSourceFactory = {
            UserListDataSource(api)
        }
    ).flow.cachedIn(viewModelScope)
}


