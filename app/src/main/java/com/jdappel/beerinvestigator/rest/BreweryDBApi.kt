package com.jdappel.beerinvestigator.rest

import retrofit2.http.GET
import com.jdappel.beerinvestigator.model.Beer
import retrofit2.Response
import retrofit2.http.Query

/**
 * Defines the API endpoints that are used with `Retrofit` to access BreweryDB
 */
interface BreweryDBApi {
    @GET("http://api.brewerydb.com/v2/search?type=beer")
    suspend fun getBeers(@Query("q") query: String?): Response<BreweryDBResponse<Beer>>
}