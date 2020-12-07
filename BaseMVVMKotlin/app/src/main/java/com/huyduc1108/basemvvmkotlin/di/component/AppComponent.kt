package com.huyduc1108.basemvvmkotlin.di.component

import android.app.Application
import com.huyduc1108.basemvvmkotlin.di.builder.ActivityModule
import com.huyduc1108.basemvvmkotlin.App
import com.huyduc1108.basemvvmkotlin.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}