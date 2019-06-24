package com.jay.mvvmdaggerkotlin.login

import com.google.gson.annotations.SerializedName

//Note that this is constructor itself & not the fields...
// AND EVERY DATA CLASS MUST HAVE ATLEAST ONE PRIMARY CONSTRUCTOR PARAMETER
data class LoginRequestModel(
        @SerializedName("username") val username: String,
        @SerializedName("password") val password: String,
        @SerializedName("hostName") val hostName: String?=null
)