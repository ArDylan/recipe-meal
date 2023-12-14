package com.dylan.recipemeal.ui.screen.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dylan.recipemeal.data.model.MealEntity
import com.dylan.recipemeal.data.model.MealsItem
import com.dylan.recipemeal.data.repository.MealRepository
import com.dylan.recipemeal.ui.components.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: MealRepository
) : ViewModel() {
    private val _MealData = mutableStateOf<UiState<MealsItem>>(UiState.Loading)
    val mealData: State<UiState<MealsItem>>
        get() = _MealData

    private val _isFavorite: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isFavorite: MutableStateFlow<Boolean>
        get() = _isFavorite

    fun getCocktailDetail(id: String) {
        viewModelScope.launch {
            repository.getMealDetail(id).observeForever {
                when (it) {
                    is UiState.Loading -> _MealData.value = UiState.Loading
                    is UiState.Success -> _MealData.value = UiState.Success(it.data)
                    is UiState.Error -> _MealData.value = UiState.Error(it.errorMessage)
                }
            }
        }
    }

    fun isCocktailFavorite(id: String) {
        viewModelScope.launch {
            repository.isMealFavorite(id)
                .catch {
                    _isFavorite.value = false
                }
                .collect { isFavorite ->
                    _isFavorite.value = isFavorite
                }
        }
    }

    fun toggleFavoriteCocktail(meal: MealEntity) {
        viewModelScope.launch {
            if (_isFavorite.value) {
                repository.deleteFavoriteMeal(meal)
            } else {
                repository.addFavoriteMeal(meal)
            }
        }
    }
}