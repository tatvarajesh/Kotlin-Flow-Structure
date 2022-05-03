package com.example.demokotlinflow.demo2.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demokotlinflow.R
import com.example.demokotlinflow.demo2.base.Status
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.flow.collectLatest

class MainActivity2 : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel
    private lateinit var userListAdapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        initViews()
        initObservers()
    }

    private fun initObservers() {
        //way 1
//        lifecycleScope.launchWhenCreated {
//            userViewModel.userListStateFlow.collect { clsUserResponse ->
//                progressBar.visibility = View.GONE
//                clsUserResponse.data?.let {
//                    it.users.let { it1 -> userListAdapter.addData(it1) }
//                    userListAdapter.notifyDataSetChanged()
//                }
//            }
//        }
//
//        lifecycleScope.launchWhenCreated {
//            userViewModel.errorStateFlow.collect {
//                progressBar.visibility = View.GONE
//                if (it.isNotEmpty())
//                    Toast.makeText(this@MainActivity2, "" + it, Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        lifecycleScope.launchWhenCreated {
//            userViewModel.loadingStateFlow.collect {
//                if (it) {
//                    progressBar.visibility = View.VISIBLE
//                } else {
//                    progressBar.visibility = View.GONE
//                }
//            }
//        }

        //way 2
//        lifecycleScope.launchWhenCreated {
//            userViewModel.userStateFlow.collect {
//
//                when (it.status) {
//                    Status.SUCCESS -> {
//                        progressBar.visibility = View.GONE
//                        it.data?.let {
//                            it.data?.users?.let { it1 -> userListAdapter.addData(it1) }
//                            userListAdapter.notifyDataSetChanged()
//                        }
//                    }
//
//                    Status.LOADING -> {
//                        progressBar.visibility = View.VISIBLE
//                    }
//
//                    Status.ERROR -> {
//                        progressBar.visibility = View.GONE
//                        Toast.makeText(this@MainActivity2, "" + it.message, Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                }
//
//            }
//        }

        //way 3
        lifecycleScope.launchWhenCreated {
            userViewModel.userListSharedFlow.collectLatest { clsUserResponse ->
                progressBar.visibility = View.GONE
                clsUserResponse.data?.let {
                    it.users.let { it1 -> userListAdapter.addData(it1) }
                    userListAdapter.notifyDataSetChanged()
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            userViewModel.errorSharedFlow.collectLatest {
                progressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity2, "" + it, Toast.LENGTH_SHORT)
                    .show()
            }
        }

        lifecycleScope.launchWhenCreated {
            userViewModel.loadingSharedFlow.collectLatest {
                if (it) {
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun initViews() {
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        userViewModel.callUserListApi()

        userListAdapter = UserListAdapter(arrayListOf(), this)
        rcvUserList.adapter = userListAdapter

        val layoutManager = LinearLayoutManager(this)
        rcvUserList.layoutManager = layoutManager
    }
}



