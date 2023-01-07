package com.example.cardbinapp.ui.screens.result

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.cardbinapp.ui.theme.topAppBarBg
import com.example.cardbinapp.ui.theme.topAppBarContentColor
import com.example.cardbinapp.R

@Composable
fun ResultTopBar() {

    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.result_top_bar_title),
                color = MaterialTheme.colors.topAppBarContentColor)
        },
        backgroundColor = MaterialTheme.colors.topAppBarBg
    )
}