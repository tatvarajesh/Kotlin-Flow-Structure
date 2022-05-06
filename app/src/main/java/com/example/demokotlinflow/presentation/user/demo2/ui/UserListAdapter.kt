package com.example.demokotlinflow.presentation.user.demo2.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demokotlinflow.R
import com.example.demokotlinflow.presentation.user.demo2.base.ClsUserResponse


class UserListAdapter(
    private val users: ArrayList<ClsUserResponse.Data.User>,
    private val context: Context
) : RecyclerView.Adapter<UserListAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var txtUser: AppCompatTextView = itemView.findViewById(R.id.txtUser)
        private var imgUser: AppCompatImageView = itemView.findViewById(R.id.imgUser)

        fun bind(user: ClsUserResponse.Data.User, context: Context) {
            txtUser.text = user.name
            Glide.with(context)
                .load(user.image)
                .circleCrop()
                .into(imgUser)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user_list, parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position], context)
    }

    fun addData(list: List<ClsUserResponse.Data.User>) {
        users.addAll(list)
    }
}