package com.huyduc1108.basemvvmkotlin.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class AuthInterceptor(private val accessToken: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader(
            "Authorization", accessToken
        ).build()
        return chain.proceed(request)
    }
}
