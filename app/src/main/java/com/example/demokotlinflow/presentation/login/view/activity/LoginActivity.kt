package com.example.demokotlinflow.presentation.login.view.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.demokotlinflow.R
import com.example.demokotlinflow.presentation.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initViews()
        initObservers()

    }

    private fun initObservers() {
        lifecycleScope.launchWhenCreated {
            loginViewModel.loginStateFlow.collect { loginResponse ->
                progressBar.visibility = View.GONE
                loginResponse.let {
                    if (it.name?.isNotEmpty() == true)
                        Toast.makeText(this@LoginActivity, "" + it.name, Toast.LENGTH_SHORT).show()
                }

            }
        }

        lifecycleScope.launchWhenCreated {
            loginViewModel.errorStateFlow.collect {
                progressBar.visibility = View.GONE
                if (it.isNotEmpty())
                    Toast.makeText(this@LoginActivity, "" + it, Toast.LENGTH_SHORT).show()
            }
        }

        lifecycleScope.launchWhenCreated {
            loginViewModel.loadingStateFlow.collect {
                if (it) {
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun initViews() {
        fabLogin.setOnClickListener {
            if (TextUtils.isEmpty(tietMobile.text.toString()) && TextUtils.isEmpty(tietPassword.text.toString())) {
                Toast.makeText(this, "Enter credentials.", Toast.LENGTH_SHORT).show()
            } else if (TextUtils.isEmpty(tietMobile.text.toString())) {
                Toast.makeText(this, "Enter mobile number.", Toast.LENGTH_SHORT).show()
            } else if (TextUtils.isEmpty(tietPassword.text.toString())) {
                Toast.makeText(this, "Enter password.", Toast.LENGTH_SHORT).show()
            } else {
                loginViewModel.callLoginApi(
                    tietMobile.text.toString(),
                    tietPassword.text.toString()
                )
            }
        }
    }
}