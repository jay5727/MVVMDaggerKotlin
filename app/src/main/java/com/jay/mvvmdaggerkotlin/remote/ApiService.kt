package com.jay.mvvmdaggerkotlin.remote

import com.jay.mvvmdaggerkotlin.login.LoginResponseModel
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {

    @GET("v2/5aa7f9042f0000d9328ea84c")
    fun login(): Call<LoginResponseModel>

}