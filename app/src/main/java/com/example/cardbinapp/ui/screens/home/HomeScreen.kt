package com.example.cardbinapp.ui.screens.home

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.cardbinapp.ui.viewmodels.SharedViewModel
import com.example.cardbinapp.utils.Constants.SUCCESS_SCREEN

@Composable
fun HomeScreen(
    sharedViewModel: SharedViewModel,
    navigateToResultScreen: () -> Unit,
    navigateToHistoryScreen: () -> Unit,
) {

    LaunchedEffect(key1 = true) {
        sharedViewModel.changeToHistoryState(false)
    }

    val scaffoldState = rememberScaffoldState()
    val currentScreen by sharedViewModel.currentScreen
    val homeItemsVisibility by sharedViewModel.homeItemsVisibility
    val homeButtonEnabled by sharedViewModel.homeButtonEnabled
    val context = LocalContext.current
    val activity = (LocalContext.current as? Activity)

    BackHandler {
        activity?.finish()
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { HomeTopBar(onHistoryBtnClicked = { navigateToHistoryScreen() }) },
        content = {
            HomeContent(
                currentScreen = currentScreen,
                context = context,
                navigateToResultScreen = navigateToResultScreen,
                homeItemsVisibility = homeItemsVisibility,
                homeButtonEnabled = homeButtonEnabled,
                onSearchClicked = {
                    sharedViewModel.searchCardBin(it)
                    sharedViewModel.changeParams(
                        visible = false,
                        screen = SUCCESS_SCREEN,
                        buttonEnabled = false
                    )
                }
            )
        }
    )
}