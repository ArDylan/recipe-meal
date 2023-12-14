package com.dylan.recipemeal.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dylan.recipemeal.data.di.Injection
import com.dylan.recipemeal.data.model.MealsResponse
import com.dylan.recipemeal.ui.ViewModelFactory
import com.dylan.recipemeal.ui.components.Loading
import com.dylan.recipemeal.ui.components.MealItem
import com.dylan.recipemeal.ui.components.SearchBarComponent
import com.dylan.recipemeal.ui.components.common.UiState
import com.dylan.recipemeal.ui.components.Error

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context = LocalContext.current))
    ),
    onNavigateToDetailScreen: (String) -> Unit,
) {
    val mealsData by viewModel.mealsData

    val performSearch = { search: String ->
        viewModel.getMeals(search)
    }

    val listState = rememberLazyListState()
    val query = remember { mutableStateOf("") }

    val onRetry = { viewModel.getMeals(query.value) }


    LaunchedEffect(key1 = viewModel) {
        if (mealsData is UiState.Loading) {
            viewModel.getMeals("")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column {
            SearchBarComponent(
                modifier = modifier,
                query = query.value,
                onQueryChange = { text: String -> query.value = text },
                onSearch = performSearch,
            )

            Spacer(modifier = Modifier.height(6.dp))

            when (mealsData) {
                is UiState.Loading -> {
                    Loading()
                }

                is UiState.Success -> {
                    val meals = (mealsData as UiState.Success<MealsResponse>).data.meals!!

                    LazyColumn(
                        state = listState,
                        contentPadding = PaddingValues(bottom = 10.dp)
                    ) {
                        items(meals, key = { it.idMeal }) {
                            MealItem(
                                modifier = modifier,
                                data = it,
                                onNavigateToDetailScreen = onNavigateToDetailScreen
                            )
                        }
                    }
                }

                is UiState.Error -> {
                    Error(
                        message = (mealsData as UiState.Error).errorMessage,
                        onRetry = onRetry,
                    )
                }
            }
        }
    }
}