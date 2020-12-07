package com.huyduc1108.basemvvmkotlin.api

import com.huyduc1108.basemvvmkotlin.data.response.ApiArrayResponse
import com.huyduc1108.basemvvmkotlin.ui.main.data.Token
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST(ApiConstants.API_LOGIN)
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<ApiArrayResponse<Token?>>
}