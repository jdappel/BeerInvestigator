package com.jdappel.beerinvestigator.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    @Singleton
    @IODispatcher
    fun provideDispatchers() : CoroutineDispatcher {
        return Dispatchers.IO
    }
}