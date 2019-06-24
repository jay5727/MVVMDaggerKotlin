package com.jay.mvvmdaggerkotlin.di.component

import com.jay.mvvmdaggerkotlin.base.BaseActivity
import com.jay.mvvmdaggerkotlin.base.BaseApplication
import com.jay.mvvmdaggerkotlin.di.module.*
import com.jay.mvvmdaggerkotlin.view.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf
(AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class))
interface AppComponent {

    // Base class injection not supported in Dagger 2 - https://github.com/google/dagger/issues/73
    // For example:
    // void inject(Activity activity);
    // void inject(Fragment fragment);

    // Application
    fun inject(application: BaseApplication)

    fun inject(activity: BaseActivity)
    fun inject(activity: LoginActivity)
}