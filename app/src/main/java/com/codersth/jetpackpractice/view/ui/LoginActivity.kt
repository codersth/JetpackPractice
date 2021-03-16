package com.codersth.jetpackpractice.view.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codersth.jetpackpractice.R
import com.codersth.jetpackpractice.databinding.ActivityLoginBinding
import com.codersth.jetpackpractice.service.repository.UserRepository
import com.codersth.jetpackpractice.viewmodel.LoginViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

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

        private class LoginViewModelFactory(): ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                // Create VM with passing a test UserRepository.
                return modelClass.getConstructor(UserRepository::class.java).newInstance(UserRepository())
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    /**
     * ViewModel to provide data for Views.
     */
    private val mLoginViewModel: LoginViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        // Retrieve layout binding.
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        // Bind binding's lifecycle to the current component.
        binding.lifecycleOwner = this
        // instantiate ViewModel with its no-args constructor.
        binding.viewModel = mLoginViewModel
        // Load user for retrieving the latest changes which will change the name field.
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            mLoginViewModel.loadUser()
        }, DURATION_SIMULATE_REQUEST)
        // Observe the login result and show corresponding message.
        mLoginViewModel.loginResult.observe(this, { success ->
            if(success) {
                Toast.makeText(applicationContext, "login success", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "login failed", Toast.LENGTH_LONG).show()
            }
        })
    }
}