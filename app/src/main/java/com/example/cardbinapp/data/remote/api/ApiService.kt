package com.example.cardbinapp.data.remote.api

import com.example.cardbinapp.domain.model.CardModel
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("/{cardBin}")
    suspend fun searchCardInfoRemote(@Path("cardBin") cardBin: String): Response<CardModel>
}