package com.huyduc1108.basemvvmkotlin.data.response

import com.google.gson.annotations.SerializedName

data class ApiArrayResponse<T>(
    @SerializedName("status")
    var status: Int? = null,
    @SerializedName("response")
    var response: T? = null,
    @SerializedName("error_code")
    var errorCode: Int? = null,
    @SerializedName("error_message")
    var errorMessage: String? = null
)