package com.codersth.jetpackpractice.view.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.codersth.jetpackpractice.R
import com.codersth.jetpackpractice.databinding.ActivityLoginBinding
import com.codersth.jetpackpractice.viewmodel.LoginViewModel

/**
 * Login Activity with the below two functions:
 * 1) Show local name automatically which had been input by user for convenience if possible.
 * 2) Show messages by toast no matter login failed or success.
 * @author zhanglei1
 * @date 2021/3/9-13:34
 * @since V1.0.0
 */
class LoginActivity : AppCompatActivity() {

    companion object {
        private const val DURATION_SIMULATE_REQUEST = 2000L
    }

    /**
     * ViewModel to provide data for Views.
     */
    private lateinit var mLoginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Retrieve layout binding.
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        // Bind binding's lifecycle to the current component.
        binding.lifecycleOwner = this
        // instantiate ViewModel with its no-args constructor.
        val loginViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(LoginViewModel::class.java)
        binding.viewModel = loginViewModel
        // Load user for retrieving the latest changes which will change the name field.
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            loginViewModel.loadUser()
        }, DURATION_SIMULATE_REQUEST)
        mLoginViewModel = loginViewModel
    }
}