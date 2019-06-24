package com.jay.mvvmdaggerkotlin.data

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

abstract class NetworkBoundResource<RequestType>
@MainThread constructor() {

    private var result = MediatorLiveData<Resource<RequestType>>() //RepoSear

    init {
        // Send loading state to UI
        result.value = Resource.loading()
        fetchFromNetwork()
    }

    @MainThread
    private fun setValue(newValue: Resource<RequestType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork() {
        try {
            val apiResponse = createCall()
            result.addSource(apiResponse) { response ->
                result.removeSource(apiResponse)

                when (response) {
                    is ApiSuccessResponse -> {
                        setValue(Resource.success(processResponse(response)))
                    }

                    is ApiErrorResponse -> {
                        onFetchFailed()
                        setValue(Resource.error(response.errorMessage))
                    }
                }
            }
        } catch (ex: Exception) {
            Log.d("TAG", ex.message)
        }
    }

    // Called when the fetch fails. The child class may want to reset components
    // like rate limiter.
    protected open fun onFetchFailed() {}

    // Returns a LiveData object that represents the resource that's implemented
    // in the base class.
    fun asLiveData() = result as LiveData<Resource<RequestType>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    // Called to create the API call.
    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

}
