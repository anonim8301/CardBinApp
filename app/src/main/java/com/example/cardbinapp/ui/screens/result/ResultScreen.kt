package com.example.cardbinapp.ui.screens.result

import androidx.activity.compose.BackHandler
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.cardbinapp.ui.viewmodels.SharedViewModel
import com.example.cardbinapp.utils.Constants.SEARCH_SCREEN


@Composable
fun ResultScreen(
    sharedViewModel: SharedViewModel,
    onBackPressed: (toHistoryScreen: Boolean) -> Unit,
) {
    val currentCard by sharedViewModel.currentCard.collectAsState()
    val toHistoryScreen by sharedViewModel.toHistoryScreen
    val context = LocalContext.current

    BackHandler() {
        sharedViewModel.resetCurrentCardToNull()
        sharedViewModel.changeParams(true, true, SEARCH_SCREEN)
        onBackPressed(toHistoryScreen)
    }

    Scaffold(
        topBar = { ResultTopBar() },
        content = {
            ResultContent(currentCard = currentCard, context = context)
        })
}