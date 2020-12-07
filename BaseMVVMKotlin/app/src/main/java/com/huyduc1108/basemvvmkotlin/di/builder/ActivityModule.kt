package com.huyduc1108.basemvvmkotlin.di.builder

import com.huyduc1108.basemvvmkotlin.ui.main.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}