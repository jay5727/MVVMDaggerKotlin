package com.jay.mvvmdaggerkotlin.di.module

import androidx.lifecycle.ViewModelProvider
import com.jay.mvvmdaggerkotlin.di.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Dagger module which provides instance of [ViewModelFactory]
 */
@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}