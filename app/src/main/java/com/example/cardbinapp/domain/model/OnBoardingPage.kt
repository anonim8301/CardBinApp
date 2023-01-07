package com.example.cardbinapp.domain.model

import androidx.annotation.DrawableRes
import com.example.cardbinapp.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String,
) {
    object First : OnBoardingPage(
        image = R.drawable.ic_logo,
        title = "Welcome",
        description = "Do you need more information about card issuer? If you do, this app is what you need!"
    )

    object Second : OnBoardingPage(
        image = R.drawable.card_bin_explain,
        title = "What is CardBin?",
        description = "The first 6 to 8 numbers on a payment card which identify the financial institution that issued the card."
    )
}