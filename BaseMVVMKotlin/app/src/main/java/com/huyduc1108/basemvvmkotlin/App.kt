package com.huyduc1108.basemvvmkotlin

import android.app.Application
import com.huyduc1108.basemvvmkotlin.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {
    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this)

    }

    override fun androidInjector(): AndroidInjector<Any> = activityDispatchingAndroidInjector

}