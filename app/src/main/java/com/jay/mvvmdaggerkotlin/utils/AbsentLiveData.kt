package com.jay.mvvmdaggerkotlin.utils

import androidx.lifecycle.LiveData


/**
 * A LiveData class that has `null` value.
 */
class AbsentLiveData<T : Any?>() : LiveData<T>() {
    init {
        // use post instead of set since this can be created on any thread
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return AbsentLiveData()
        }
    }
}