package com.dylan.recipemeal.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Favorite : Screen("favorite")
    data object About : Screen("profile")
    data object DetailMeal : Screen("detail/{idMeal}") {
        fun createRoute(idMeal: String) = "detail/$idMeal"
    }
}