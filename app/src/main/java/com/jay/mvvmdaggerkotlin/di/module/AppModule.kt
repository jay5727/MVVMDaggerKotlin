package com.jay.mvvmdaggerkotlin.di.module

import com.jay.mvvmdaggerkotlin.base.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger module acts as an instance provider during dependency injection, when all modules
 * are searched for methods providing matching instance type.
 *
 *
 * Note: method parameters are fulfilled by other @Providers.
 * MAKE sure to add in AppComponent module Array
 */
@Module
class AppModule(val app: BaseApplication) {

    @Provides
    @Singleton
    fun provideApp() = app

//    @Provides
//    @Singleton
//    fun providesSharedPrefences() = app.getSharedPreferences("PREFS", Context.MODE_PRIVATE)
}
