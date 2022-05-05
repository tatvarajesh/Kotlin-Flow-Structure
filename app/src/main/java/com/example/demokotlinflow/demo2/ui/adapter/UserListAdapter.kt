package com.example.demokotlinflow.demo2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demokotlinflow.databinding.ItemUserListBinding
import com.example.demokotlinflow.demo2.base.ClsUserResponse
import com.example.demokotlinflow.demo2.ui.MainActivity

class UserListAdapter(private var context: MainActivity) :
    PagingDataAdapter<ClsUserResponse.Data.User, UserListAdapter.UserListViewHolder>(
        UserListsComparator
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserListViewHolder {
        return UserListViewHolder(
            ItemUserListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindUserList(it) }
    }

    inner class UserListViewHolder(private val binding: ItemUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindUserList(user: ClsUserResponse.Data.User) = with(binding) {
            txtUser.text = user.name
            Glide.with(context)
                .load(user.image)
                .circleCrop()
                .into(imgUser)
        }
    }

    object UserListsComparator : DiffUtil.ItemCallback<ClsUserResponse.Data.User>() {
        override fun areItemsTheSame(oldItem: ClsUserResponse.Data.User, newItem: ClsUserResponse.Data.User): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ClsUserResponse.Data.User, newItem: ClsUserResponse.Data.User): Boolean {
            return oldItem == newItem
        }
    }
}