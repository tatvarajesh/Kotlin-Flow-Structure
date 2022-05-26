package com.example.demokotlinflow.data.addon.remote

import com.example.demokotlinflow.data.addon.remote.response.AddOnResponse
import com.example.demokotlinflow.domain.addon.entity.AddOnEntity

fun AddOnResponse.map() = this.customer_addons?.map {
    AddOnEntity(0,
        it?.id,
        it?.addon_name,
        it?.addon_price,
        it?.discount_amount,
        it?.addon_usage_status
    )
}