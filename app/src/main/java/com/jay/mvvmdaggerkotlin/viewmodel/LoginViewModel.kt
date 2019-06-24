package com.jay.mvvmdaggerkotlin.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jay.mvvmdaggerkotlin.login.LoginRequestModel
import com.jay.mvvmdaggerkotlin.login.LoginResponseModel
import com.jay.mvvmdaggerkotlin.repository.ILoginRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(val loginRepository: ILoginRepository) : ViewModel() {

    //@Inject
    //lateinit var repository: ILoginRepository

    var isLoading = MutableLiveData<Boolean>()

    var apiError = MutableLiveData<Throwable>()

    val email = ObservableField<String>()
    val password = ObservableField<String>()
    //val isLoading = ObservableBoolean(false)

    var errorEmail = ObservableField<String>("")
    var errorPassword = ObservableField<String>("")

    var _loginResponseLiveData: MutableLiveData<LoginResponseModel> = MutableLiveData()

    /*
     * LiveData observed by the UI
     *
     */
    fun getLoginResponseLiveData(): LiveData<LoginResponseModel> {
        return _loginResponseLiveData
    }

    fun onLoginClicked() {

        if (validateInputs()) {
            val loginRequestModel = LoginRequestModel(email.get()!!, password.get()!!)

            isLoading.value = true
            loginRepository.getLoginSuccess(
                    loginRequestModel,
                    {
                        _loginResponseLiveData.value = it
                        isLoading.value = false
                    },
                    {
                        apiError.value = it
                        isLoading.value = false
                    })
        }

    }

    private fun validateInputs(): Boolean {
        var isValid = true

        if (email.get().isNullOrBlank()) {
            errorEmail.set("Invalid username")
            isValid = false
        } else {
            errorEmail.set(null)
        }

        if (password.get().isNullOrBlank() || password.get()!!.length < 4) {
            errorPassword.set("Password too short")
            isValid = false
        } else {
            errorPassword.set(null)
        }
        return isValid
    }

}