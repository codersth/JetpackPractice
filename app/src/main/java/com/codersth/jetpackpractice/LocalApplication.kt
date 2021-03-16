package com.codersth.jetpackpractice

import android.app.Activity
import android.app.Application
import com.codersth.jetpackpractice.service.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 *
 * @author zhanglei1
 * @date 2021/3/16-11:19
 * @since V1.0.0
 */
class LocalApplication: Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingActivityInjector
}