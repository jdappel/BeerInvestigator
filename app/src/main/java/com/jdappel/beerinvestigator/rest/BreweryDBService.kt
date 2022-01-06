package com.jdappel.beerinvestigator.rest


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
    fun createBreweryDBService(apiToken: String): BreweryDBApi {
        val builder = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(JacksonConverterFactory.create())
            .baseUrl("https://api.brewerydb.com/v2/")
        if (apiToken.isNotEmpty()) {
            val client = OkHttpClient.Builder().addInterceptor { chain: Interceptor.Chain ->
                val request = chain.request()
                val url = request.url().newBuilder().addQueryParameter("key", apiToken).build()
                val newReq = request.newBuilder().url(url).build()
                chain.proceed(newReq)
            }.build()
            builder.client(client)
        }
        return builder.build().create(BreweryDBApi::class.java)
    }
}