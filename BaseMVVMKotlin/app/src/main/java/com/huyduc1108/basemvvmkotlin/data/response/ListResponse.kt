package com.huyduc1108.basemvvmkotlin.data.response

import com.huyduc1108.basemvvmkotlin.utils.Define


class ListResponse<T> {
    var status: Define.ResponseStatus? = null
        private set
    var data: List<T?>? = null
        private set
    var error: Throwable? = null
        private set

    constructor()
    private constructor(
        status: Define.ResponseStatus,
        data: List<T?>?,
        error: Throwable?
    ) {
        this.status = status
        this.data = data
        this.error = error
    }

    fun loading(): ListResponse<T> {
        return ListResponse(
            Define.ResponseStatus.LOADING,
            null,
            null
        )
    }

    fun success(data: List<T?>?): ListResponse<T> {
        return ListResponse(
            Define.ResponseStatus.SUCCESS,
            data,
            null
        )
    }

    fun error(throwable: Throwable): ListResponse<T> {
        return ListResponse(
            Define.ResponseStatus.ERROR,
            null,
            throwable
        )
    }
}