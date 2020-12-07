package com.huyduc1108.basemvvmkotlin.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.huyduc1108.basemvvmkotlin.di.ViewModelFactory
import com.huyduc1108.basemvvmkotlin.di.key.ViewModelKey

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
//    @Binds
//    @IntoMap
//    @ViewModelKey(NearViewModel::class)
//    abstract fun bindNearViewModel(viewModel: NearViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


}