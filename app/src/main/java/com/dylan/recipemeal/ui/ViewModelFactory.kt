package com.dylan.recipemeal.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dylan.recipemeal.data.di.Injection
import com.dylan.recipemeal.data.repository.MealRepository
import com.dylan.recipemeal.ui.screen.detail.DetailViewModel
import com.dylan.recipemeal.ui.screen.favorite.FavoriteViewModel
import com.dylan.recipemeal.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: MealRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

//    fun getViewModelFactory(context: Context): ViewModelFactory {
//        return ViewModelFactory(
////            Injection.provideUserRepository(context),
//        )
//    }
}