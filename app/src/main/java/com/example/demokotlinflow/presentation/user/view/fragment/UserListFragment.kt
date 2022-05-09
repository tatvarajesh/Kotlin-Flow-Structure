package com.example.demokotlinflow.presentation.user.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demokotlinflow.data.user.remote.response.UserListResponse
import com.example.demokotlinflow.databinding.FragmentUserListBinding
import com.example.demokotlinflow.presentation.user.adapter.UserListAdapter
import com.example.demokotlinflow.presentation.user.viewmodel.UserListViewModel


class UserListFragment : Fragment() {
    private val userListViewModel: UserListViewModel by activityViewModels()
    private lateinit var userListAdapter: UserListAdapter
    private var binding: FragmentUserListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (binding == null) {
            binding = FragmentUserListBinding.inflate(inflater, container, false)
            initViews()
            initObservers()
            if (savedInstanceState == null) {
                userListViewModel.callUserListApi(0)
            }
        }
        return binding?.root!!
    }

    private fun initViews() {
        binding?.btnCallApi?.setOnClickListener {
            userListViewModel.callUserListApi(10)
        }

        userListAdapter = UserListAdapter(arrayListOf(), requireContext())
        binding?.rcvUserList?.adapter = userListAdapter
    }

    private fun initObservers() {
        //way 1
        lifecycleScope.launchWhenCreated {
            userListViewModel.userListStateFlow.collect { clsUserResponse ->
                binding?.progressBar?.visibility = View.GONE
                clsUserResponse.data?.let {
                    userListAdapter = UserListAdapter(
                        it.users as ArrayList<UserListResponse.Data.User>,
                        requireContext()
                    )
                    binding?.rcvUserList?.adapter = userListAdapter
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            userListViewModel.errorStateFlow.collect {
                binding?.progressBar?.visibility = View.GONE
                if (it.isNotEmpty())
                    Toast.makeText(requireContext(), "" + it, Toast.LENGTH_SHORT).show()
            }
        }

        lifecycleScope.launchWhenCreated {
            userListViewModel.loadingStateFlow.collect {
                if (it) {
                    binding?.progressBar?.visibility = View.VISIBLE
                } else {
                    binding?.progressBar?.visibility = View.GONE
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
}