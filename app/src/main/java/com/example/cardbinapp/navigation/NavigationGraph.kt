package com.example.cardbinapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cardbinapp.ui.screens.history.HistoryScreen
import com.example.cardbinapp.ui.screens.home.HomeScreen
import com.example.cardbinapp.ui.screens.result.ResultScreen
import com.example.cardbinapp.ui.screens.splash.SplashScreen
import com.example.cardbinapp.ui.screens.welcome.WelcomeScreen
import com.example.cardbinapp.ui.viewmodels.SharedViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(navController: NavHostController, sharedViewModel: SharedViewModel) {

    val onBoardingCompleted by sharedViewModel.onBoardingCompleted.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    ) {

        composable(route = Screens.Splash.route) {
            SplashScreen(navigateToHomeScreen = {
                navController.popBackStack()
                if (onBoardingCompleted) {
                    navController.navigate(Screens.Home.route)
                } else {
                    navController.navigate(Screens.Welcome.route)
                }
            })
        }
        composable(route = Screens.Welcome.route) {
            WelcomeScreen(
                sharedViewModel = sharedViewModel,
                navigateToHomeScreen = {
                    navController.popBackStack()
                    navController.navigate(Screens.Home.route)
                }
            )
        }
        composable(route = Screens.Home.route) {
            HomeScreen(
                sharedViewModel = sharedViewModel,
                navigateToResultScreen = { navController.navigate(Screens.Result.route) },
                navigateToHistoryScreen = {
                    navController.navigate(Screens.History.route)
                    sharedViewModel.getAllCards()
                    sharedViewModel.changeToHistoryState(false)
                }
            )
        }
        composable(route = Screens.Result.route) {
            ResultScreen(
                sharedViewModel = sharedViewModel,
                onBackPressed = { toHistoryState ->
                    if (toHistoryState) {
                        navController.navigate(Screens.History.route)
                    } else {
                        navController.navigate(Screens.Home.route) {
                        }
                    }
                }
            )
        }
        composable(
            route = Screens.History.route,
        ) {
            HistoryScreen(
                sharedViewModel = sharedViewModel,
                navigateToResultScreen = { navController.navigate(Screens.Result.route) },
                navigateToHomeScreen = { navController.navigate(Screens.Home.route) })
        }
    }
}