package com.jdappel.beerinvestigator.rest;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Defines the API endpoints that are used with {@code Retrofit} to access BreweryDB
 */
public interface BreweryDBApi {

    @GET("http://api.brewerydb.com/v2/search?type=beer")
    Observable<BreweryDBResponse<Beer>> getBeers(@Query("q") String query);
}
