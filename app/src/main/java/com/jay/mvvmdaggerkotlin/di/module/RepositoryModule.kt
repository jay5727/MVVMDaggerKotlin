package com.jay.mvvmdaggerkotlin.di.module

import com.jay.mvvmdaggerkotlin.remote.ApiService
import com.jay.mvvmdaggerkotlin.repository.ILoginRepository
import com.jay.mvvmdaggerkotlin.repository.LoginRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * MAKE sure to add in AppComponent module Array
 */
@Module
class RepositoryModule() {

    @Provides
    @Singleton
    fun provideLoginRepository(apiService: ApiService): ILoginRepository = LoginRepository(apiService)

}
