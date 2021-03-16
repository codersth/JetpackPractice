package com.codersth.jetpackpractice.service.repository

import com.codersth.jetpackpractice.model.User
import javax.inject.Inject

/**
 *
 * @author zhanglei1
 * @date 2021/3/15-17:16
 * @since V1.0.0
 */
class UserRepository @Inject constructor() {

    /**
     * Save user to DB.
     * @return true means insert successfully, otherwise failed.
     */
    fun saveUser(use: User): Boolean {
        return false
    }
}