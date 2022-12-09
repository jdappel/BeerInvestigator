package com.jdappel.beerinvestigator.data.network


import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.schedulers.Schedulers
import retrofit2.converter.jackson.JacksonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.Interceptor

/**
 * Package-private implementation class for constructing a REST client with `Retrofit` and
 * `OkHttp`
 */
internal object BreweryDBService {
    fun createBreweryDBService(): BreweryDBApi {
        val builder = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(JacksonConverterFactory.create())
            .baseUrl("https://api.openbrewerydb.com/")
        val client = OkHttpClient.Builder().build()
        builder.client(client)
        return builder.build().create(BreweryDBApi::class.java)
    }
}