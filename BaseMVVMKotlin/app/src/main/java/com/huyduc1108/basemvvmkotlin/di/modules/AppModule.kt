package com.huyduc1108.basemvvmkotlin.di.modules

import android.app.Application
import android.content.Context
import com.huyduc1108.basemvvmkotlin.BuildConfig
import com.huyduc1108.basemvvmkotlin.api.ApiService
import com.huyduc1108.basemvvmkotlin.data.AppDatabase
import com.huyduc1108.basemvvmkotlin.di.CoroutineScropeIO
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, CoreDataModule::class, DaoModule::class, RemoteDataSourceModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideAppContext(app: Application): Context = app

    @CoroutineScropeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

    @Singleton
    @Provides
    fun provideApiService(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, ApiService::class.java)

    private fun createRetrofit(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    private fun <T> provideService(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory,
        clazz: Class<T>
    ): T {
        return createRetrofit(okhttpClient, converterFactory).create(clazz)
    }
}