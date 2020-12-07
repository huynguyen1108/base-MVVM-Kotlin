package com.huyduc1108.basemvvmkotlin.data.response

import com.huyduc1108.basemvvmkotlin.utils.Define


class ObjectResponse<T> {
    var status: Define.ResponseStatus? = null
        private set
    var data: T? = null
        private set
    var error: Throwable? = null
        private set

    constructor()

    private constructor(status: Define.ResponseStatus, data: T, error: Throwable?) {
        this.status = status
        this.data = data
        this.error = error
    }

    fun loading(): ObjectResponse<T?> {
        return ObjectResponse(
            Define.ResponseStatus.LOADING,
            null,
            null
        )
    }

    fun success(data: T): ObjectResponse<T> {
        return ObjectResponse(
            Define.ResponseStatus.SUCCESS,
            data,
            null
        )
    }

    fun error(throwable: Throwable): ObjectResponse<T?> {
        return ObjectResponse(
            Define.ResponseStatus.ERROR,
            null,
            throwable
        )
    }
}