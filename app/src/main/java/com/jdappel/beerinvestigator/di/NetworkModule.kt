package com.jdappel.beerinvestigator.di

import com.jdappel.beerinvestigator.BuildConfig
import com.jdappel.beerinvestigator.data.network.BreweryDBApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * `Dagger` module for constructing a REST client with `Retrofit`
 */
@Module
object NetworkModule {
    @JvmStatic
    @Provides
    fun provideBreweryAPI(): BreweryDBApi {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://api.openbrewerydb.com/").build().create(BreweryDBApi::class.java)
    }

    @Provides
    @Singleton
    fun providesOkHttp() =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()
}