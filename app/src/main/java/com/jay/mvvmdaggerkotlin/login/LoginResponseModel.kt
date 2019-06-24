package com.jay.mvvmdaggerkotlin.login

import com.google.gson.annotations.SerializedName

//{
//    "username":"jay5727",
//    "password":"123456",
//    "responsecode":"1"
//}
data class LoginResponseModel(@SerializedName("username") val username: String,
                              @SerializedName("password") val password: String,
                              @SerializedName("responsecode") val responsecode: String
)
