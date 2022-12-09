package com.jdappel.beerinvestigator.data.network

import com.jdappel.beerinvestigator.data.network.BreweryDBService.createBreweryDBService
import dagger.Module
import dagger.Provides

/**
 * `Dagger` module for constructing a REST client with `Retrofit`
 */
@Module
object BreweryDBServiceModule {
    @JvmStatic
    @Provides
    fun provideBreweryAPI(): BreweryDBApi {
        return createBreweryDBService()
    }
}