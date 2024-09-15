package com.example.submissioncompose.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Favorite : Screen("favorite")
    data object Profile : Screen("profile")
    data object Detail : Screen("detail")
}