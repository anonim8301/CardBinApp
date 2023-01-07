package com.example.cardbinapp.di

import android.content.Context
import androidx.room.Room
import com.example.cardbinapp.data.local.CardDb
import com.example.cardbinapp.data.local.dao.CardDao
import com.example.cardbinapp.utils.Constants.CARD_BIN_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun providesDataBase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
        context,
        CardDb::class.java,
        CARD_BIN_DATABASE

    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideDao(db: CardDb): CardDao {
        return db.cardDao()
    }
}