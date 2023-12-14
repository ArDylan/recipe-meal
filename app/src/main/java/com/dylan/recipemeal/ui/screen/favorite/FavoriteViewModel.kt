package com.dylan.recipemeal.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dylan.recipemeal.data.model.MealEntity
import com.dylan.recipemeal.data.repository.MealRepository
import com.dylan.recipemeal.ui.components.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: MealRepository
) : ViewModel() {
    private val _favoriteMeals: MutableStateFlow<UiState<List<MealEntity>>> = MutableStateFlow(UiState.Loading)
    val favoriteMeals: MutableStateFlow<UiState<List<MealEntity>>>
        get() = _favoriteMeals

    fun getFavoriteMeals() {
        viewModelScope.launch {
            repository.getFavoriteMeals()
                .catch {
                    _favoriteMeals.value = UiState.Error(it.message.toString())
                }
                .collect { meals ->
                    _favoriteMeals.value = UiState.Success(meals)
                }
        }
    }
}