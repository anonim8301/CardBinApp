package com.example.cardbinapp.domain.model

data class Country(
    val alpha2: String,
    val currency: String,
    val emoji: String,
    val latitude: Float,
    val longitude: Float,
    val name: String,
    val numeric: String,
)