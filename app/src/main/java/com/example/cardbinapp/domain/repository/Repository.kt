package com.example.cardbinapp.domain.repository

import com.example.cardbinapp.domain.model.CardModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Repository {

    suspend fun searchCardInfoRemote(cardBin: String): Response<CardModel>

    fun getCardFromDb(cardId: Int): Flow<CardModel>

    fun getAllCards(): Flow<List<CardModel>>

    suspend fun addCardToDb(card: CardModel)

    suspend fun deleteAllCards()

}