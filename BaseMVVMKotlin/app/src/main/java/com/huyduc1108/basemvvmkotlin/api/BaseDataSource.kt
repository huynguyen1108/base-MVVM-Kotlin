package com.huyduc1108.basemvvmkotlin.api

import com.huyduc1108.basemvvmkotlin.data.Result
import retrofit2.Response

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                return Result.success(body)
            }
            return error(" ${response.code()} ${response.message()}")

        } catch (e: Exception) {
            if(e.message != null && e.message!!.contains("BEGIN_OBJECT but was BEGIN_ARRAY")){
                return Result.error(code = 1)
            }
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Result<T> {

        return Result.error("Network call has failed for a following reason: $message")
    }
}