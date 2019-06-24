package com.jay.mvvmdaggerkotlin.data

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 *
 */

data class Resource<out T>(val status: Status, val data: T? = null, val message: String? = null) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    val isSuccess: Boolean
        get() = status == Status.SUCCESS && data != null

    val isLoading: Boolean
        get() = status == Status.LOADING

    val isLoaded: Boolean
        get() = status != Status.LOADING

    companion object {

        /**
         * Creates [Resource] object with `SUCCESS` status and [data].
         */
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data = data)
        }


        /**
         * Creates [Resource] object with `ERROR` status and [message].
         */
        fun <T> error(msg: String): Resource<T> {
            return Resource(Status.ERROR, message = msg)
        }

        /**
         * Creates [Resource] object with `LOADING` status to notify
         * the UI to showing loading.
         */
        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING)
        }
    }
}