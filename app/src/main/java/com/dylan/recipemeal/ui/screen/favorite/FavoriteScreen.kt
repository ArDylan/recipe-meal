package com.dylan.recipemeal.ui.screen.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dylan.recipemeal.data.di.Injection
import com.dylan.recipemeal.data.model.MealEntity
import com.dylan.recipemeal.ui.ViewModelFactory
import com.dylan.recipemeal.ui.components.Loading
import com.dylan.recipemeal.ui.components.Error
import com.dylan.recipemeal.ui.components.MealItem
import com.dylan.recipemeal.ui.components.common.UiState
import com.dylan.recipemeal.ui.theme.RecipeMealTheme

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context = LocalContext.current))
    ),
    onNavigateToDetailScreen: (String) -> Unit,
) {
    viewModel.favoriteMeals.collectAsState(initial = UiState.Loading).value.let {
        when (it) {
            is UiState.Loading -> {
                Loading()
                viewModel.getFavoriteMeals()
            }

            is UiState.Success -> {
                FavoriteContent(
                    modifier = modifier,
                    data = it.data,
                    onNavigateToDetailScreen = onNavigateToDetailScreen,
                )
            }

            is UiState.Error -> {
                Error(
                    message = it.errorMessage,
                    onRetry = { viewModel.getFavoriteMeals() }
                )
            }
        }
    }
}

@Composable
fun FavoriteContent(
    modifier: Modifier = Modifier,
    data: List<MealEntity>,
    onNavigateToDetailScreen: (String) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (data.isNotEmpty()) {
            LazyColumn(
                state = rememberLazyListState(),
                contentPadding = PaddingValues(bottom = 10.dp)
            ) {
                items(data, key = { it.idMeal }) {
                    MealItem(
                        modifier = modifier,
                        data = it,
                        onNavigateToDetailScreen = onNavigateToDetailScreen
                    )
                }
            }
        } else {
            Text(
                text = "No Favorite Meals",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = modifier.align(alignment = Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    RecipeMealTheme {
        FavoriteContent(
            data = listOf()
        )
    }
}