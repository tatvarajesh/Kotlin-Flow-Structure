package com.example.demokotlinflow.presentation.addon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demokotlinflow.data.addon.remote.response.AddOnResponse
import com.example.demokotlinflow.databinding.ItemAddOnBinding
import com.example.demokotlinflow.domain.addon.entity.AddOnEntity
import java.util.*

class AddOnAdapter(
    val context: Context,
    private var myList: List<AddOnEntity>,
    private val onItemClicked: (position: Int) -> Unit
) :
    RecyclerView.Adapter<AddOnAdapter.AddOnViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddOnViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding =
            ItemAddOnBinding.inflate(layoutInflater, parent, false)
        return AddOnViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: AddOnViewHolder, position: Int) {
        holder.setData(myList[position], context)
        holder.itemView.setOnClickListener {
            onItemClicked(position)
        }
    }


    class AddOnViewHolder(private val itemBinding: ItemAddOnBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun setData(model: AddOnEntity, context: Context) {
            itemBinding.customerAddon = model
        }
    }
}