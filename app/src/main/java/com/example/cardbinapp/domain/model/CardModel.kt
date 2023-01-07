package com.example.cardbinapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cardbinapp.utils.Constants.CARD_TABLE_NAME

@Entity(tableName = CARD_TABLE_NAME)
data class CardModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val bank: Bank,
    val brand: String? = "",
    val country: Country,
    val number: Number,
    val prepaid: Boolean?,
    val scheme: String,
    val type: String?,
)