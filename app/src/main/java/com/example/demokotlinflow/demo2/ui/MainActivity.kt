package com.example.demokotlinflow.demo2.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demokotlinflow.R
import com.example.demokotlinflow.demo2.base.BaseService
import com.example.demokotlinflow.demo2.ui.adapter.UserListAdapter
import com.example.demokotlinflow.demo2.ui.adapter.UserListLoadStateAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel
    private lateinit var userAdapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        setupList()
        initObservers()
    }

    private fun initObservers() {
        btnCallApi.setOnClickListener {
            progressBar.visibility=View.VISIBLE

            lifecycleScope.launch {
                userViewModel.userList.collectLatest { pagedData ->
                    lifecycleScope.launch(Dispatchers.Default) {
                        progressBar.visibility=View.GONE
                    }
                    userAdapter.submitData(pagedData)
                }
            }
        }
    }

    private fun initViews() {
        val factory = UserListViewModelFactory(BaseService())
        userViewModel = ViewModelProviders.of(this, factory).get(UserViewModel::class.java)
    }

    private fun setupList() {
        userAdapter = UserListAdapter(this)
        rcvUserList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter.withLoadStateHeaderAndFooter(
                header = UserListLoadStateAdapter { userAdapter.retry() },
                footer = UserListLoadStateAdapter { userAdapter.retry() }
            )
            setHasFixedSize(true)
        }
    }

}



