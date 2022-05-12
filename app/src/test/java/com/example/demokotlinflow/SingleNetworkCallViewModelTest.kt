package com.example.demokotlinflow

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.demokotlinflow.data.base.Resource
import com.example.demokotlinflow.domain.user.UserListRepository
import com.example.demokotlinflow.domain.user.entity.UserListEntity
import com.example.demokotlinflow.domain.user.usecase.UserListUseCase
import com.example.demokotlinflow.presentation.user.viewmodel.UserListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SingleNetworkCallViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var userListRepository: UserListRepository

    @Mock
    private lateinit var userListUseCase: UserListUseCase

    @Mock
    private lateinit var apiUsersObserver: Observer<UserListEntity>

    @Mock
    lateinit var context: Context


    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        context = Mockito.mock(Context::class.java)
        testCoroutineRule.runBlockingTest {
            doReturn(flowOf(UserListEntity()))
                .`when`(userListRepository).getUserList(0, 10)

//            val viewModel = UserListViewModel(userListUseCase, context)
//            viewModel.getUsers().observeForever(apiUsersObserver)
//            verify(userListRepository).getUserList(0, 10)
//            verify(apiUsersObserver).onChanged(UserListEntity())
//            viewModel.getUsers().removeObserver(apiUsersObserver)

        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Exception"
            doReturn(flow<UserListEntity> {
                throw IllegalStateException(errorMessage)
            }).`when`(userListRepository).getUserList(0, 10)
        }
    }

}