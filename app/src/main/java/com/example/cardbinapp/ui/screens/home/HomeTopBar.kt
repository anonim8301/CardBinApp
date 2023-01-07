package com.example.cardbinapp.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.cardbinapp.ui.theme.SMALL_PADDING
import com.example.cardbinapp.ui.theme.topAppBarBg
import com.example.cardbinapp.ui.theme.topAppBarContentColor
import com.example.cardbinapp.R

@Composable
fun HomeTopBar(
    onHistoryBtnClicked: () -> Unit,
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.topAppBarBg,
        title = {
            Text(
                text = stringResource(R.string.home_app_bar_title),
                color = MaterialTheme.colors.topAppBarContentColor)
        },
        actions = {
            HistoryAction(onHistoryBtnClicked = onHistoryBtnClicked)
        }
    )
}


@Composable
fun HistoryAction(
    onHistoryBtnClicked: () -> Unit,
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = { expanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_menu),
            contentDescription = stringResource(id = R.string.home_history_action),
            tint = Color.White
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onHistoryBtnClicked()
                }) {
                Text(
                    modifier = Modifier.padding(start = SMALL_PADDING),
                    text = stringResource(R.string.home_appbar_history_text),
                    style = MaterialTheme.typography.subtitle1,
                )
            }
        }
    }
}