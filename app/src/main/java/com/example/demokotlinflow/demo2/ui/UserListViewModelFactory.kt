package com.example.demokotlinflow.demo2.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demokotlinflow.demo2.base.BaseApi
import com.example.demokotlinflow.demo2.base.BaseService

class UserListViewModelFactory(
    private val api: BaseService
) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(api) as T
    }
}