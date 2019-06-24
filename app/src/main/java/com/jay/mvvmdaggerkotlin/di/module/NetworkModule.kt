package com.jay.mvvmdaggerkotlin.di.module

import com.jay.mvvmdaggerkotlin.BuildConfig
import com.jay.mvvmdaggerkotlin.remote.ApiService
import com.jay.mvvmdaggerkotlin.utils.CustomHttpLogging
import com.jay.mvvmdaggerkotlin.utils.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Module which provides all required dependencies about network
 *
 * MAKE sure to add in AppComponent module Array
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(CustomHttpLogging())
        //val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d("Retrofit", message) })
        interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }


    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
    }

    /**
     * Provides the Api service implementation.
     * @param okHttpClient the OkHttpClient object
     * @return the ApiService implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideApiInterface(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
                .baseUrl("http://www.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .client(okHttpClient)
                .build()
                .create(ApiService::class.java)
    }
}