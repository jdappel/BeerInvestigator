package com.jdappel.beerinvestigator.data.network


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Package-private implementation class for constructing a REST client with `Retrofit` and
 * `OkHttp`
 */
internal object BreweryDBService {
    fun createBreweryDBService(): BreweryDBApi {
        val builder = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://api.openbrewerydb.com/")
        val client = OkHttpClient.Builder().build()
        builder.client(client)
        return builder.build().create(BreweryDBApi::class.java)
    }
}