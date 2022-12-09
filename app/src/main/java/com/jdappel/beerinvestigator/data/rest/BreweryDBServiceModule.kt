package com.jdappel.beerinvestigator.data.rest

import com.jdappel.beerinvestigator.data.rest.BreweryDBService.createBreweryDBService
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
        return createBreweryDBService("948fd17f5f5d6560f06d6533f18af582")
    }
}