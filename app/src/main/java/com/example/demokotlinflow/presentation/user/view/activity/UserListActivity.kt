package com.example.demokotlinflow.presentation.user.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demokotlinflow.R
import com.example.demokotlinflow.data.user.remote.response.UserListResponse
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
        if (savedInstanceState == null) {
            userListViewModel.callUserListApi(0)
        }

    }

    private fun initObservers() {
        //way 1
        lifecycleScope.launchWhenCreated {
            userListViewModel.userListStateFlow.collect { clsUserResponse ->
                progressBar.visibility = View.GONE
                clsUserResponse.data?.let {
                    userListAdapter = UserListAdapter(
                        it.users as ArrayList<UserListResponse.Data.User>,
                        this@UserListActivity
                    )
                    rcvUserList.adapter = userListAdapter
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

        //way 3
        /*lifecycleScope.launchWhenCreated {
            userListViewModel.userListSharedFlow.collectLatest { clsUserResponse ->
                progressBar.visibility = View.GONE
                clsUserResponse.data?.let {
                    userListAdapter = UserListAdapter(
                        it.users as ArrayList<UserListResponse.Data.User>,
                        this@UserListActivity
                    )
                    rcvUserList.adapter = userListAdapter
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            userListViewModel.errorSharedFlow.collectLatest {
                progressBar.visibility = View.GONE
                Toast.makeText(this@UserListActivity, "" + it, Toast.LENGTH_SHORT)
                    .show()
            }
        }

        lifecycleScope.launchWhenCreated {
            userListViewModel.loadingSharedFlow.collectLatest {
                if (it) {
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                }
            }
        }*/

    }

    private fun initViews() {
        btnCallApi.setOnClickListener {
            userListViewModel.callUserListApi(10)
        }

        btnMoveToFragment.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        userListAdapter = UserListAdapter(arrayListOf(), this)
        rcvUserList.adapter = userListAdapter
    }


}