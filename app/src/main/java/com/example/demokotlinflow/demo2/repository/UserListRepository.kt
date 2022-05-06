package com.example.demokotlinflow.demo2.repository

import com.example.demokotlinflow.demo2.base.BaseApi
import com.example.demokotlinflow.demo2.base.ClsUserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserListRepository(private val baseApi: BaseApi) {

    /***
     * Function which will call the api and it will return a Flow.
     * Return a flow, It asynchronously performs calculation or function
     * and the emit function emits the data to the receivers which are listening
     * to this flow.
     */
    suspend fun getUserList(offset: Int, limit: Int): Flow<ClsUserResponse> {
        return flow {
            //get the comment Data from the api
            val userList = baseApi.getAllUser(offset, limit)

            //Emit this data wrapped in the helper class [CommentApiState]
            emit(userList)
        }.flowOn(Dispatchers.IO)
    }
}