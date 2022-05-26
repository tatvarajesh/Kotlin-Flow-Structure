package com.example.demokotlinflow.domain.addon.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.demokotlinflow.data.addon.remote.response.AddOnResponse

@Entity(tableName = "table_addon")
data class AddOnEntity(
        @PrimaryKey(autoGenerate = true) var index: Int = 0,
        var id: Int? = null,
        var addon_name: String? = null,
        var addon_price: String? = null,
        var discount_amount: Int? = null,
        var addon_usage_status: String? = null
)