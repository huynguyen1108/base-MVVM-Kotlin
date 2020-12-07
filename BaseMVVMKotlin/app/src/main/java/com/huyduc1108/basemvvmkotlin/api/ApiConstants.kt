package com.huyduc1108.basemvvmkotlin.api

object ApiConstants {
    private const val VERSION_V0: String = "api/v0/"
    const val API_LOGIN: String = "${VERSION_V0}login"

    //ERROR
    const val ERROR_MAIL: Int = 99
    const val ERROR_PASS: Int = 98
    const val ERROR_API: Int = 0

    //SUCCESS
    const val SUCCESS_API: Int = 1
}
