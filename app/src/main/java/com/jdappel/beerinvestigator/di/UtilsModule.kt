package com.jdappel.beerinvestigator.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
object UtilsModule {
    @Provides
    @Singleton
    @IODispatcher
    fun provideDispatchers() : CoroutineDispatcher {
        return Dispatchers.IO
    }
}