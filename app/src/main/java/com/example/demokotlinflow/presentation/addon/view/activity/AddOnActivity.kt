package com.example.demokotlinflow.presentation.addon.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.demokotlinflow.R
import com.example.demokotlinflow.data.addon.remote.response.AddOnResponse
import com.example.demokotlinflow.domain.addon.entity.AddOnEntity
import com.example.demokotlinflow.presentation.addon.view.fragment.AddOnDetailFragment
import com.example.demokotlinflow.presentation.addon.view.fragment.AddOnListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.toolbar_addon.*

@AndroidEntryPoint
class AddOnActivity : AppCompatActivity() {
    private lateinit var fragment: Fragment
    private lateinit var navController: NavController

    companion object {
        var addOnPos = 0
        var addOnList: ArrayList<AddOnEntity> = arrayListOf()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_on)
        initViews()
        setClickListeners()
    }

    private fun setClickListeners() {
        imgBack.setOnClickListener {
            navController.popBackStack()
        }
    }

    private fun initViews() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    fun navigate(destination: Int) {
        navController.navigate(destination)
    }

    fun manageToolBar() {
        fragment = getForegroundFragment()?.childFragmentManager?.fragments?.get(0)!!
        if (fragment is AddOnListFragment) {
            imgBack.visibility = View.INVISIBLE
        } else if (fragment is AddOnDetailFragment) {
            imgBack.visibility = View.VISIBLE
        }
    }


    private fun getForegroundFragment(): Fragment? {
        return supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
    }
}