package com.jdappel.beerinvestigator.rest;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Defines the API endpoints that are used with {@code Retrofit} to access BreweryDB
 */
public interface BreweryDBApi {

    @GET("http://api.brewerydb.com/v2/search?type=beer")
    Observable<BreweryDBResponse<Beer>> getBeers(@Query("q") String query);
}
