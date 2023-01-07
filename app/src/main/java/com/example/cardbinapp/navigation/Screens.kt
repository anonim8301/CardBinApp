package com.example.cardbinapp.navigation

sealed class Screens(val route: String) {
    object Splash : Screens("splash_screen")
    object Welcome : Screens("welcome_screen")
    object Home : Screens("home_screen")
    object Result : Screens("result_screen")
    object History : Screens("history_screen") {
    }
}
