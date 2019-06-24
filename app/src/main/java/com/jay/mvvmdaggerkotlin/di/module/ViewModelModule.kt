package com.jay.mvvmdaggerkotlin.di.module

import androidx.lifecycle.ViewModel
import com.jay.mvvmdaggerkotlin.di.ViewModelKey
import com.jay.mvvmdaggerkotlin.viewmodel.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * This Dagger module acts as an instance provider for [ViewModel] during dependency injection.
 * NOTE : The ViewModelModule is used to provide a map of view models through dagger that is used by the ViewModelFactory class.
 */
@Module
abstract class ViewModelModule {

    /*
     * This method basically says
     * inject this object into a Map using the @IntoMap annotation,
     * with the  LoginViewModel.class as key,
     * and a Provider that will build a LoginViewModel
     * object.
     *
     */
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    protected abstract fun providesLoginViewModel(viewModel: LoginViewModel): ViewModel

}