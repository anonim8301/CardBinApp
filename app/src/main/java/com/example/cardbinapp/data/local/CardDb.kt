package com.example.cardbinapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cardbinapp.domain.model.CardModel
import com.example.cardbinapp.data.local.dao.CardDao

@Database(entities = [CardModel::class], version = 3)
@TypeConverters(com.example.cardbinapp.data.local.TypeConverters::class)
abstract class CardDb() : RoomDatabase() {

    abstract fun cardDao() : CardDao
}