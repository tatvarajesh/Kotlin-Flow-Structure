package com.example.demokotlinflow.presentation.addon.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.demokotlinflow.R
import com.example.demokotlinflow.data.addon.remote.response.AddOnResponse
import com.example.demokotlinflow.databinding.FragmentAddOnDetailBinding
import com.example.demokotlinflow.domain.addon.entity.AddOnEntity
import com.example.demokotlinflow.presentation.addon.view.activity.AddOnActivity
import com.example.demokotlinflow.presentation.base.ActivityFragmentAnnotation
import com.example.demokotlinflow.presentation.base.BaseFragment
import com.example.demokotlinflow.util.GsonBuilderUtil
import kotlinx.coroutines.MainScope

@ActivityFragmentAnnotation(contentId = R.layout.fragment_add_on_detail)
class AddOnDetailFragment : BaseFragment<FragmentAddOnDetailBinding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()

    }

    private fun setClickListeners() {

    }

    override fun initViews() {
        (activity as AddOnActivity).manageToolBar()
        val addOnJsonString = arguments?.getString("addOn")
        val addOnDetail: AddOnEntity? = GsonBuilderUtil().getGsonParser()?.fromJson(addOnJsonString,AddOnEntity::class.java)
            binding.txtId.text = "Id: " + addOnDetail?.id.toString()
        binding.txtName.text = "Name: " + addOnDetail?.addon_name
        binding.txtPrice.text = "Price: " + addOnDetail?.addon_price
        binding.txtDiscountAmount.text =
            "Discount Amount: " + addOnDetail?.discount_amount.toString()
        binding.txtUsageStatus.text = "Usage Status: " + addOnDetail?.addon_usage_status
    }

    override fun initObservers() {

    }

    override fun apiCalls() {

    }


}