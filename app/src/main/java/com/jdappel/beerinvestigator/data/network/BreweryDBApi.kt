package com.jdappel.beerinvestigator.data.network

import retrofit2.http.GET
import com.jdappel.beerinvestigator.data.model.Beer
import com.jdappel.beerinvestigator.data.model.BreweryDBResponse
import retrofit2.Response
import retrofit2.http.Query

/**
 * Defines the API endpoints that are used with `Retrofit` to access BreweryDB
 */
interface BreweryDBApi {
    @GET("/breweries")
    suspend fun getBeers(@Query("q") query: String?): Response<BreweryDBResponse<Beer>>

    companion object {
        const val name = "by_name"
        const val city = "by_city"
        const val postalCode = "by_postal"
        const val state = "by_state"
        const val distance = "by_dist"
        const val type = "by_type"
        const val sort = "sort"
        const val page = "page"
    }
}