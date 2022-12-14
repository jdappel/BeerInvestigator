package com.jdappel.beerinvestigator.di

import com.jdappel.beerinvestigator.data.network.BreweryDBApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * `Dagger` module for constructing a REST client with `Retrofit`
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @JvmStatic
    @Provides
    fun provideBreweryAPI(): BreweryDBApi {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://api.openbrewerydb.org/").build().create(BreweryDBApi::class.java)
    }
}