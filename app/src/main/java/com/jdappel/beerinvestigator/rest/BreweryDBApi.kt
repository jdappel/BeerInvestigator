package com.jdappel.beerinvestigator.rest

import retrofit2.http.GET
import com.jdappel.beerinvestigator.rest.BreweryDBResponse
import com.jdappel.beerinvestigator.rest.Beer
import io.reactivex.Observable
import retrofit2.http.Query

/**
 * Defines the API endpoints that are used with `Retrofit` to access BreweryDB
 */
interface BreweryDBApi {
    @GET("http://api.brewerydb.com/v2/search?type=beer")
    fun getBeers(@Query("q") query: String?): Observable<BreweryDBResponse<Beer>>
}