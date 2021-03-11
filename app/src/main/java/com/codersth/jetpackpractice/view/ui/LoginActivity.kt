package com.codersth.jetpackpractice.view.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.codersth.jetpackpractice.R
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

    /**
     * ViewModel to provide data for Views.
     */
    private lateinit var mLoginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // instantiate ViewModel with its no-args constructor.
        mLoginViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(LoginViewModel::class.java)
        val nameView = findViewById<TextView>(R.id.name_et)
        val passwordView = findViewById<TextView>(R.id.password_et)
        // Observe name changes and display the changed value to View.
        mLoginViewModel.name.observe(this, { name ->
            nameView?.text = name
        })
        // Load user for retrieving the latest changes which will change the name field.
        mLoginViewModel.loadUser()
        findViewById<View>(R.id.login_btn)?.setOnClickListener {
            // Simple checking.
            if(TextUtils.isEmpty(nameView.text) || TextUtils.isEmpty(passwordView.text)) {
                Toast.makeText(this, "Invalid name or password(name = ${nameView.text}, password = ${passwordView.text}", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            // Do Login Executing.
            mLoginViewModel.login(nameView.text.toString(), passwordView.text.toString())
        }
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