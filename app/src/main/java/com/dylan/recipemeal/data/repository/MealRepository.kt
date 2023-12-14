package com.dylan.recipemeal.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dylan.recipemeal.data.api.ApiService
import com.dylan.recipemeal.data.local.FavoriteDao
import com.dylan.recipemeal.data.model.MealEntity
import com.dylan.recipemeal.data.model.MealsItem
import com.dylan.recipemeal.data.model.MealsResponse
import com.dylan.recipemeal.ui.components.common.UiState
import kotlinx.coroutines.Dispatchers

class MealRepository private constructor(
    private val apiService: ApiService,
    private val favoriteDao: FavoriteDao
) {

    fun getMeals(search: String): LiveData<UiState<MealsResponse>> = liveData(Dispatchers.IO) {
        emit(UiState.Loading)

        try {
            val response = apiService.getMeals(search)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    if (!body.meals.isNullOrEmpty()) {
                        emit(UiState.Success(body))
                    } else {
                        emit(UiState.Error("Not Found"))
                    }
                } else {
                    emit(UiState.Error("Something went wrong"))
                }
            } else {
                emit(UiState.Error("Something went wrong"))
            }
        } catch (e: Exception) {
            emit(UiState.Error(e.message.toString()))
        }
    }

    fun getMealDetail(id: String): LiveData<UiState<MealsItem>> = liveData(Dispatchers.IO) {
        emit(UiState.Loading)

        try {
            val response = apiService.getMealDetail(id)
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.meals != null && body.meals.isNotEmpty()) {
                    emit(UiState.Success(body.meals[0]))
                } else {
                    emit(UiState.Error("Not Found"))
                }
            } else {
                emit(UiState.Error("Something went wrong"))
            }
        } catch (e: Exception) {
            emit(UiState.Error(e.message.toString()))
        }
    }

    fun isMealFavorite(id: String) = favoriteDao.isFavorite(id)

    fun getFavoriteMeals() = favoriteDao.getFavoriteMeals()

    suspend fun deleteFavoriteMeal(meal: MealEntity) =
        favoriteDao.delete(meal.idMeal)

    suspend fun addFavoriteMeal(meal: MealEntity) = favoriteDao.insert(meal)

    companion object {
        @Volatile
        private var instance: MealRepository? = null
        fun getInstance(
            apiService: ApiService,
            favoriteDao: FavoriteDao
        ): MealRepository =
            instance ?: synchronized(this) {
                instance ?: MealRepository(apiService, favoriteDao)
            }.also { instance = it }
    }

}