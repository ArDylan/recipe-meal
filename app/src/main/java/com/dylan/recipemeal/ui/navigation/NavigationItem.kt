package com.dylan.recipemeal.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.dylan.recipemeal.ui.navigation.Screen

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)