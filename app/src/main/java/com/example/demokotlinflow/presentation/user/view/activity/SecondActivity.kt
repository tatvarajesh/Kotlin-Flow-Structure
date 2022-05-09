package com.example.demokotlinflow.presentation.user.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.demokotlinflow.R
import com.example.demokotlinflow.presentation.user.view.fragment.UserListFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        if (savedInstanceState == null) {
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragmentView, UserListFragment())
            ft.commit()
        }
    }
}