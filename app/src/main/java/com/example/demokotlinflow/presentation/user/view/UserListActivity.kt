package com.example.demokotlinflow.presentation.user.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demokotlinflow.R
import com.example.demokotlinflow.presentation.user.adapter.UserListAdapter
import com.example.demokotlinflow.presentation.user.viewmodel.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_user_list.*

@AndroidEntryPoint
class UserListActivity : AppCompatActivity() {
    private val userListViewModel: UserListViewModel by viewModels()
    private lateinit var userListAdapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        initViews()
        initObservers()
    }

    private fun initObservers() {
        //way 1
        lifecycleScope.launchWhenCreated {
            userListViewModel.userListStateFlow.collect { clsUserResponse ->
                progressBar.visibility = View.GONE
                clsUserResponse.data?.let {
                    it.users.let { it1 -> userListAdapter.addData(it1) }
                    userListAdapter.notifyDataSetChanged()
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            userListViewModel.errorStateFlow.collect {
                progressBar.visibility = View.GONE
                if (it.isNotEmpty())
                    Toast.makeText(this@UserListActivity, "" + it, Toast.LENGTH_SHORT).show()
            }
        }

        lifecycleScope.launchWhenCreated {
            userListViewModel.loadingStateFlow.collect {
                if (it) {
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                }
            }
        }

    }

    private fun initViews() {
        userListViewModel.callUserListApi()

        userListAdapter = UserListAdapter(arrayListOf(), this)
        rcvUserList.adapter = userListAdapter

        val layoutManager = LinearLayoutManager(this)
        rcvUserList.layoutManager = layoutManager
    }
}