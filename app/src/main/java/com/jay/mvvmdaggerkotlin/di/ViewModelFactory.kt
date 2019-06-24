package com.jay.mvvmdaggerkotlin.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * Custom ViewModelProvider factory class for creating instances of viewModels for dependency injection in fragments and activities.
 * ViewModels are injected into viewModelsMap by using a custom annotation [ViewModelKey] which acts as key for respective ViewModel.
 * https://stackoverflow.com/questions/44270577/android-lifecycle-library-viewmodel-using-dagger-2
 *
 * The ViewModelFactory class has a list of ViewModels and will provide the corresponding ViewModel in activity/fragment
 *
 */
class ViewModelFactory @Inject constructor(private val viewModelsMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
        ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = viewModelsMap[modelClass] ?: viewModelsMap.asIterable().firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        return try {
            creator.get() as T
        } catch (e: Exception) {
            //throw RuntimeException(e)
            throw IllegalArgumentException("Can't find creator/provider for ViewModel class ${modelClass.simpleName}")
        }
    }

}