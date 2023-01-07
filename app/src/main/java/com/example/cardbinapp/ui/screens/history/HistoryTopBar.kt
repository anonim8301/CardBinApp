package com.example.cardbinapp.ui.screens.history

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.cardbinapp.ui.theme.buttonBgColor
import com.example.cardbinapp.ui.theme.topAppBarBg
import com.example.cardbinapp.ui.theme.topAppBarContentColor
import com.example.cardbinapp.R


@Composable
fun HistoryTopBar(
    onDeleteClicked: () -> Unit,
) {

    TopAppBar(
        title = {
            Text(text = stringResource(R.string.history_top_bar_title),
                color = MaterialTheme.colors.topAppBarContentColor)
        },
        backgroundColor = MaterialTheme.colors.topAppBarBg,
        actions = {
            DeleteAction(onDeleteClicked = onDeleteClicked)
        }
    )
}

@Composable
fun DeleteAction(
    onDeleteClicked: () -> Unit,
) {
    var openDialog by remember {
        mutableStateOf(false)
    }

    ShowAlertDialog(
        title = stringResource(id = R.string.delete_all_cards),
        message = stringResource(id = R.string.delete_all_cards_confirm),
        openDialog = openDialog,
        onCloseClicked = { openDialog = false },
        onYesClicked = {
            onDeleteClicked()
        }
    )

    IconButton(onClick = { openDialog = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_trash_can),
            contentDescription = "",
            tint = Color.White
        )
    }
}

@Composable
fun ShowAlertDialog(
    title: String,
    message: String,
    openDialog: Boolean,
    onCloseClicked: () -> Unit,
    onYesClicked: () -> Unit,
) {

    if (openDialog) {
        AlertDialog(
            title = {
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold
                )
            }, text = {
                Text(
                    text = message,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal
                )
            },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.buttonBgColor),
                    onClick = {
                        onYesClicked()
                        onCloseClicked()
                    }) {
                    Text(text = "Yes", color = Color.White)
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {
                        onCloseClicked()
                    }) {
                    Text(text = "No", color = Color.Black)
                }
            },
            onDismissRequest = {
                onCloseClicked()
            }
        )
    }
}