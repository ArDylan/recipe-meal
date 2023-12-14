package com.dylan.recipemeal

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dylan.recipemeal.ui.navigation.NavigationItem
import com.dylan.recipemeal.ui.navigation.Screen
import com.dylan.recipemeal.ui.screen.about.AboutScreen
import com.dylan.recipemeal.ui.screen.detail.DetailScreen
import com.dylan.recipemeal.ui.screen.favorite.FavoriteScreen
import com.dylan.recipemeal.ui.screen.home.HomeScreen

@Composable
fun App(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailMeal.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onNavigateToDetailScreen = { idMeal ->
                        navController.navigate(Screen.DetailMeal.createRoute(idMeal))
                    }
                )
            }
            composable(
                route = Screen.DetailMeal.route,
                arguments = listOf(navArgument("idMeal") { type = NavType.StringType })
            ) {
                val id = it.arguments?.getString("idMeal") ?: ""
                DetailScreen(id)
            }

            composable(Screen.About.route) {
                AboutScreen()
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(
                    onNavigateToDetailScreen = { idMeal ->
                        navController.navigate(Screen.DetailMeal.createRoute(idMeal))
                    }
                )
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController, modifier: Modifier = Modifier) {
    NavigationBar(
        modifier = modifier,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.home_nav),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.favorite_nav),
                icon = ImageVector.vectorResource(id = R.drawable.baseline_favorite_24),
                screen = Screen.Favorite
            ),
            NavigationItem(
                title = stringResource(R.string.about_nav),
                icon = Icons.Default.AccountCircle,
                screen = Screen.About
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
            )
        }
    }
}
