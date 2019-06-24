package com.jay.mvvmdaggerkotlin.view

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.jay.mvvmdaggerkotlin.R
import com.jay.mvvmdaggerkotlin.base.BaseActivity
import com.jay.mvvmdaggerkotlin.databinding.ActivityLoginBinding
import com.jay.mvvmdaggerkotlin.login.LoginResponseModel
import com.jay.mvvmdaggerkotlin.utils.extensions.getViewModel
import com.jay.mvvmdaggerkotlin.utils.extensions.showToast
import com.jay.mvvmdaggerkotlin.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private val viewModel by lazy { getViewModel<LoginViewModel>() }

    private lateinit var activityLoginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        initDataBinding()

        button.setOnClickListener {
            viewModel.onLoginClicked()
        }
        attachObserver()

    }

    private fun attachObserver() {

        viewModel.isLoading.observe(this, Observer<Boolean> {
            it?.let { showLoadingDialog(it) }
        })

        viewModel.getLoginResponseLiveData().observe(this, Observer<LoginResponseModel> {
            it?.let { showToast(it.responsecode) }
        })

        viewModel.apiError.observe(this, Observer<Throwable> {
            it?.let { Snackbar.make(findViewById(android.R.id.content), it.localizedMessage, Snackbar.LENGTH_LONG).show() }
        })

    }

    private fun initDataBinding() {
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        activityLoginBinding.setLifecycleOwner(this);
        activityLoginBinding.viewModel = viewModel
    }

    private fun showLoadingDialog(show: Boolean) {
        if (show) progressBar.visibility = View.VISIBLE else progressBar.visibility = View.GONE
    }

}
