package com.huyduc1108.basemvvmkotlin.ui.main.ui

import com.huyduc1108.basemvvmkotlin.R
import com.huyduc1108.basemvvmkotlin.base.BaseActivity
import com.huyduc1108.basemvvmkotlin.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun layoutId(): Int = R.layout.activity_main

    override fun viewModelClass(): Class<MainViewModel>? = MainViewModel::class.java
}