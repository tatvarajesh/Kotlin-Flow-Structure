package com.example.demokotlinflow.presentation.addon.view.fragment

import com.example.demokotlinflow.data.addon.remote.response.AddOnResponse
import com.example.demokotlinflow.domain.addon.entity.AddOnEntity

object FakeAddOnData {

    val addonList = arrayOf(
        AddOnEntity(0,1,"name 1","50",100,"00"),
        AddOnEntity(1,2,"name 2","100",100,"00"),
        AddOnEntity(2,3,"name 3","150",100,"00"),
        AddOnEntity(3,4,"name 4","200",100,"00"),
    )

}