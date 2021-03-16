package com.codersth.jetpackpractice.viewmodel

import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.codersth.jetpackpractice.BR
import com.codersth.jetpackpractice.service.repository.UserRepository
import com.codersth.jetpackpractice.util.ObservableViewModel
import javax.inject.Inject
import kotlin.random.Random

/**
 * @author zhanglei1
 * @date 2021/3/9-16:14
 * @since V1.0.0
 */
class LoginViewModel @Inject constructor(private val repository: UserRepository): ObservableViewModel() {

    companion object {
        private const val TAG = "LoginViewModel"
    }

    /**
     * Simply define a string field to express Model.
     */
    private var nameData = ""

    private var passwordData = ""
    /**
     * For caller to observe the login result.
     */
    val loginResult = MutableLiveData<Boolean>()

    /**
     * Annotation as [Bindable] for two-way binding with view.
     */
    @Bindable
    fun getName(): String {
        return nameData
    }

    @Bindable
    fun getPassword(): String {
        return passwordData
    }

    fun setName(newValue: String) {
        if(nameData != newValue) {
            nameData = newValue
            notifyPropertyChanged(BR.name)
        }
    }

    fun setPassword(newValue: String) {
        if(newValue != passwordData) {
            passwordData = newValue
            notifyPropertyChanged(BR.password)
        }
    }

    fun loadUser() {
        // Just set a value directly, it may a complicated process in fact, that is, retrieve data from DB or the backend.
        setName("Jetpack")
    }


    /**
     * Binding for layout and called by login clicked.
     */
    fun login() {
        Log.d(TAG, "login name = $nameData and password = $passwordData")
        // Simple checking.
        if(TextUtils.isEmpty(nameData) || TextUtils.isEmpty(passwordData)) {
//            Toast.makeText(this, "Invalid name or password(name = ${nameView.text}, password = ${passwordView.text}", Toast.LENGTH_LONG).show()
            Log.d(TAG, "Invalid name or password(name = ${nameData}, password = ${passwordData}")
            return
        }
        // Do Login Executing.
        loginResult.value = Random.nextBoolean()
    }
}