package com.huyduc1108.basemvvmkotlin.di.modules

import com.google.gson.Gson
import com.huyduc1108.basemvvmkotlin.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Dagger module to provide core data functionality.
 */
@Module
class CoreDataModule {

    @Provides
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }


    @Provides
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply { level = if (BuildConfig.DEBUG) BODY else NONE }

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideRxAdapter(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()
}
