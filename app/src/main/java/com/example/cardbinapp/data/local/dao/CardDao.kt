package com.example.cardbinapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cardbinapp.domain.model.CardModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {

    @Query("SELECT * FROM card_table ORDER BY id DESC")
    fun getAllCardBins(): Flow<List<CardModel>>

    @Query("SELECT * FROM card_table WHERE id=:cardId")
    fun getCardFromDb(cardId: Int): Flow<CardModel>

    @Insert
    suspend fun addCard(card: CardModel)

    @Query("DELETE FROM card_table")
    suspend fun deleteAllCards()
}
