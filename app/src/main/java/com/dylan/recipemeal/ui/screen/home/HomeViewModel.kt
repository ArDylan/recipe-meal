package com.dylan.recipemeal.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dylan.recipemeal.data.model.MealsResponse
import com.dylan.recipemeal.data.repository.MealRepository
import com.dylan.recipemeal.ui.components.common.UiState
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MealRepository
) : ViewModel() {
    private val _mealsData = mutableStateOf<UiState<MealsResponse>>(UiState.Loading)
    val mealsData: State<UiState<MealsResponse>>
        get() = _mealsData

    fun getMeals(search: String) {
        viewModelScope.launch {
            repository.getMeals(search).observeForever {
                when (it) {
                    is UiState.Loading -> _mealsData.value = UiState.Loading
                    is UiState.Success -> _mealsData.value = UiState.Success(it.data)
                    is UiState.Error -> _mealsData.value = UiState.Error(it.errorMessage)
                }
            }
        }
    }
}