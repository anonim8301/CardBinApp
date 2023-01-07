package com.example.cardbinapp.di

import android.content.Context
import com.example.cardbinapp.data.pref.DataStoreOperationsImpl
import com.example.cardbinapp.data.repository.RepositoryImpl
import com.example.cardbinapp.domain.repository.DataStoreOperations
import com.example.cardbinapp.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context,
    ): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideRepository(repositoryImpl: RepositoryImpl): Repository = repositoryImpl
}