package com.example.demokotlinflow.presentation.addon.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.demokotlinflow.R
import com.example.demokotlinflow.data.addon.remote.response.AddOnResponse
import com.example.demokotlinflow.databinding.FragmentAddOnListBinding
import com.example.demokotlinflow.presentation.addon.adapter.AddOnAdapter
import com.example.demokotlinflow.presentation.addon.view.activity.AddOnActivity
import com.example.demokotlinflow.presentation.addon.view.activity.AddOnActivity.Companion.addOnList
import com.example.demokotlinflow.presentation.addon.view.activity.AddOnActivity.Companion.addOnPos
import com.example.demokotlinflow.presentation.addon.viewmodel.AddOnViewModel
import com.example.demokotlinflow.presentation.base.ActivityFragmentAnnotation
import com.example.demokotlinflow.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.activity_add_on.*

@ActivityFragmentAnnotation(contentId = R.layout.fragment_add_on_list)
class AddOnListFragment : BaseFragment<FragmentAddOnListBinding>() {
    private val addOnViewModel: AddOnViewModel by activityViewModels()
    private lateinit var addOnAdapter: AddOnAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AddOnActivity).manageToolBar()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }

    private fun setClickListeners() {

    }

    override fun initViews() {
    }


    override fun initObservers() {
        lifecycleScope.launchWhenCreated {
            addOnViewModel.addOnStateFlow.collect { addOnResponse ->
                binding.progressBar.visibility = View.GONE
                addOnResponse.let {
                    if (it.customer_addons?.isEmpty() == false) {
                        addOnList = it.customer_addons as ArrayList<AddOnResponse.CustomerAddon?>
                        addOnAdapter = AddOnAdapter(requireContext(), it.customer_addons) {
                            (activity as AddOnActivity).navigate(R.id.action_addOnListFragment_to_addOnDetailFragment)
                            addOnPos = it
                        }
                        binding.rcvAddOn.adapter = addOnAdapter
                    }
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            addOnViewModel.errorStateFlow.collect {
                binding.progressBar.visibility = View.GONE
                if (it.isNotEmpty() && it.lowercase() != "null")
                    Toast.makeText(requireContext(), "" + it, Toast.LENGTH_SHORT).show()
            }
        }

        lifecycleScope.launchWhenCreated {
            addOnViewModel.loadingStateFlow.collect {
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    override fun apiCalls() {
        addOnViewModel.callAddOnApi()
    }
}