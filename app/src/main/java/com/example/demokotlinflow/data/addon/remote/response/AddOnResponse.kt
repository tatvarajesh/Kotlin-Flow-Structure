package com.example.demokotlinflow.data.addon.remote.response

data class AddOnResponse(
    var customer_addons: List<CustomerAddon?>?,
    var pagination: Pagination?
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
        var discounts_config: List<Int?>?,
        var start_date: String?,
        var expiry_date: String?,
        var addon_usage_status: String?
    )

    data class Pagination(
        var per_page: Int?,
        var current_page: Int?,
        var next_page: Int?,
        var prev_page: Any?,
        var total_pages: Int?,
        var total_count: Int?
    )
}