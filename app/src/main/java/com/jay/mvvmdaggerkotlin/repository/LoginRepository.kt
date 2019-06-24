package com.jay.mvvmdaggerkotlin.repository

import com.jay.mvvmdaggerkotlin.login.LoginRequestModel
import com.jay.mvvmdaggerkotlin.login.LoginResponseModel
import com.jay.mvvmdaggerkotlin.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(val apiService: ApiService) : ILoginRepository {

    override fun getLoginSuccess(loginRequestModel: LoginRequestModel,
                                 successHandler: (LoginResponseModel?) -> Unit,
                                 failureHandler: (Throwable?) -> Unit) {

        //intentionally not passing request model since it is a GET request,[Only required if it was a POST request]
        apiService.login().enqueue(object : Callback<LoginResponseModel> {
            override fun onResponse(call: Call<LoginResponseModel>?, response: Response<LoginResponseModel>?) {
                response?.body()?.let {
                    successHandler(it)
                }
            }

            override fun onFailure(call: Call<LoginResponseModel>?, t: Throwable?) {
                failureHandler(t)
            }
        })
    }
}

