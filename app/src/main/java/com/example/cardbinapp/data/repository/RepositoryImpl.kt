package com.example.cardbinapp.data.repository

import com.example.cardbinapp.data.local.dao.CardDao
import com.example.cardbinapp.data.remote.api.ApiService
import com.example.cardbinapp.domain.model.CardModel
import com.example.cardbinapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dbDao: CardDao,
) : Repository {
    override suspend fun searchCardInfoRemote(cardBin: String): Response<CardModel> {
        return apiService.searchCardInfoRemote(cardBin)
    }

    override fun getCardFromDb(cardId: Int): Flow<CardModel> {
       return dbDao.getCardFromDb(cardId)
    }

    override fun getAllCards(): Flow<List<CardModel>> {
        return dbDao.getAllCardBins()
    }

    override suspend fun addCardToDb(card: CardModel) {
        dbDao.addCard(card)
    }


    override suspend fun deleteAllCards() {
        dbDao.deleteAllCards()
    }
}
