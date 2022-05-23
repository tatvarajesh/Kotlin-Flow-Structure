package com.example.demokotlinflow.domain.addon.entity

import com.example.demokotlinflow.data.addon.remote.response.AddOnResponse

data class AddOnEntity(
    var customer_addons: List<AddOnResponse.CustomerAddon?>? = null
) {
    data class CustomerAddon(
        var id: Int?,
        var addon_name: String?,
        var addon_price: String?,
        var swap_count_validity: Int?,
        var discount_amount: Int?,
        var discount_percentage: Any?,
        var percentage_max_discount_amount: Any?,
        var swap_counter: Int?,
        var addon_usage_status: String?
    )
}