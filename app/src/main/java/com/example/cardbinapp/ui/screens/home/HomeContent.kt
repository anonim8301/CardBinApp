package com.example.cardbinapp.ui.screens.home

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.cardbinapp.R
import com.example.cardbinapp.components.CreditCard
import com.example.cardbinapp.ui.theme.*
import com.example.cardbinapp.utils.Constants.SEARCH_SCREEN
import com.example.cardbinapp.utils.Constants.SUCCESS_SCREEN

@Composable
fun HomeContent(
    onSearchClicked: (cardBinQuery: String) -> Unit,
    navigateToResultScreen: () -> Unit,
    homeItemsVisibility: Boolean,
    homeButtonEnabled: Boolean,
    currentScreen: String,
    context: Context,
) {

    Crossfade(
        targetState = currentScreen,
        animationSpec = tween(3000),
    ) { screen ->
        when (screen) {
            SEARCH_SCREEN -> SearchContent(
                onSearchClicked = onSearchClicked,
                context = context,
                homeButtonEnabled = homeButtonEnabled
            )
            SUCCESS_SCREEN -> {
                SuccessScreen(
                    navigateToResultScreen = navigateToResultScreen,
                    homeItemsVisibility = homeItemsVisibility,
                )
            }
        }
    }
}

@Composable
fun SearchContent(
    onSearchClicked: (cardBinQuery: String) -> Unit,
    context: Context,
    homeButtonEnabled: Boolean,
) {
    var inputCardBin by remember { mutableStateOf("") }
    val minLength = 6
    val maxLength = 8

    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.appBgColor)) {
        val (
            creditCard,
            searchField,
            searchBtn,
        ) = createRefs()
        CreditCard(
            modifier = Modifier
                .padding(LARGE_PADDING)
                .fillMaxWidth()
                .constrainAs(creditCard) {
                    top.linkTo(parent.top, margin = LARGEST_PADDING)
                },
            bankName = "Bank Name",
            imageId = R.drawable.ic_visa,
            cardBin = inputCardBin
        )

        Column(
            modifier = Modifier
                .padding(LARGE_PADDING)
                .fillMaxWidth()
                .constrainAs(searchField) {
                    top.linkTo(creditCard.bottom)
                },
        ) {
            Text(
                text = "Search",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                textAlign = TextAlign.Start,
                color = MaterialTheme.colors.contentTextColor
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = inputCardBin,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = textFieldColor,
                    cursorColor = Color.Black,
                    disabledLabelColor = textFieldColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    textColor = primaryColor
                ),
                onValueChange = {
                    if (it.length <= maxLength) {
                        inputCardBin = it
                    }
                },
                shape = RoundedCornerShape(ROUNDED_CORNER_SHAPE_DP),
                singleLine = true,
                trailingIcon = {
                    if (inputCardBin.isNotEmpty()) {
                        IconButton(onClick = { inputCardBin = "" }) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = null
                            )
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType =
                KeyboardType.NumberPassword),
            )
            Text(
                text = "${inputCardBin.length} / $maxLength",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                textAlign = TextAlign.End,
                color = handleInputCountColor(inputCardBin.length, minLength)
            )
        }

        Button(
            enabled = homeButtonEnabled,
            modifier = Modifier
                .padding(start = LARGE_PADDING, end = LARGE_PADDING)
                .fillMaxWidth()
                .constrainAs(searchBtn) {
                    bottom.linkTo(parent.bottom, margin = SMALL_PADDING)
                },
            shape = RoundedCornerShape(ROUNDED_CORNER_SHAPE_DP),
            onClick = {
                if (inputCardBin.length >= minLength) {
                    onSearchClicked(inputCardBin)
                } else {
                    makeToast(context = context)
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors
                .buttonBgColor),
        ) {
            Text(
                modifier = Modifier,
                color = Color.White,
                fontSize = 16.sp,
                text = "SEARCH"
            )
        }
    }
}

fun makeToast(context: Context) {
    Toast.makeText(
        context,
        "Fill all!",
        Toast.LENGTH_SHORT
    ).show()
}

fun handleInputCountColor(input: Int, minLength: Int): Color {
    return if (input >= minLength) primaryColor else customRed
}