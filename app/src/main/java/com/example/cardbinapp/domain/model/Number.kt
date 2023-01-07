package com.example.cardbinapp.domain.model

data class Number(
    val length: Int? = 0,
    val luhn: Boolean? = false,
    val cardBin: String? = "",
)