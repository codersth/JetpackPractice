package com.codersth.jetpackpractice.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

/**
 * @author zhanglei1
 * @date 2021/3/9-16:14
 * @since V1.0.0
 */
class LoginViewModel: ViewModel() {

    companion object {
        private const val TAG = "LoginViewModel"
    }

    /**
     * Simply define a string field to express Model.
     */
    var name = ObservableField<String>()

    /**
     * For caller to observe the login result.
     */
    val loginResult = MutableLiveData<Boolean>()

    fun loadUser() {
        // Just set a value directly, it may a complicated process in fact, that is, retrieve data from DB or the backend.
        name.set("Jetpack")
    }

    fun login(name: String, password: String) {
        Log.d(TAG, "login() name = $name password = $password")
        // Simulate Login Executing..., return a random result.
        loginResult.value = Random.nextBoolean()
    }
}