package com.jay.mvvmdaggerkotlin.repository

import com.jay.mvvmdaggerkotlin.login.LoginRequestModel
import com.jay.mvvmdaggerkotlin.login.LoginResponseModel

interface ILoginRepository {
    fun getLoginSuccess(loginRequestModel: LoginRequestModel, successHandler: (LoginResponseModel?) -> Unit, failureHandler: (Throwable?) -> Unit)
}