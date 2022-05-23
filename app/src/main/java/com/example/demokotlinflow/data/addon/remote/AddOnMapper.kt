package com.example.demokotlinflow.data.addon.remote

import com.example.demokotlinflow.data.addon.remote.response.AddOnResponse
import com.example.demokotlinflow.domain.addon.entity.AddOnEntity

fun mapAddOnData(addOnResponse: AddOnResponse?): AddOnEntity {
    return AddOnEntity(customer_addons = addOnResponse?.customer_addons)
}