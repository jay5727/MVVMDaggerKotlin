package com.jay.mvvmdaggerkotlin.base

import android.app.Application
import com.jay.mvvmdaggerkotlin.di.component.AppComponent
import com.jay.mvvmdaggerkotlin.di.component.DaggerAppComponent
import com.jay.mvvmdaggerkotlin.di.module.AppModule
import com.jay.mvvmdaggerkotlin.di.module.NetworkModule
import com.jay.mvvmdaggerkotlin.di.module.RepositoryModule

/**
 * NOTE : Make sure to register BaseApplication in [AndroidManifest]
 */
class BaseApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule)
                .repositoryModule(RepositoryModule())
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

}