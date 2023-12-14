package com.dylan.recipemeal.data.di

import android.content.Context
import com.dylan.recipemeal.data.api.ApiConfig
import com.dylan.recipemeal.data.local.FavoriteDatabase
import com.dylan.recipemeal.data.repository.MealRepository

object Injection {
    fun provideRepository(context: Context): MealRepository {
        val apiService = ApiConfig.getApiService()
        val favoriteDatabase = FavoriteDatabase.getInstance(context)
        val favoriteDao = favoriteDatabase.favoriteDao()
        return MealRepository.getInstance(apiService, favoriteDao)
    }
}