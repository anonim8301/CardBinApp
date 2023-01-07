package com.example.cardbinapp.ui.screens.history

import androidx.activity.compose.BackHandler
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.cardbinapp.ui.viewmodels.SharedViewModel


@ExperimentalMaterialApi
@Composable
fun HistoryScreen(
    sharedViewModel: SharedViewModel,
    navigateToResultScreen: () -> Unit,
    navigateToHomeScreen: () -> Unit,
) {
    LaunchedEffect(key1 = true) {
        sharedViewModel.changeToHistoryState(true)
    }
    val allCards by sharedViewModel.allCards.collectAsState()

    BackHandler {
        navigateToHomeScreen()
    }

    Scaffold(
        topBar = { HistoryTopBar(onDeleteClicked = { sharedViewModel.deleteAllCards() }) },
        content = {
            HistoryContent(
                allCards = allCards,
                onCardClicked = {
                    sharedViewModel.searchDbChangeCurrentCard(it)
                    navigateToResultScreen()
                })
        }
    )
}