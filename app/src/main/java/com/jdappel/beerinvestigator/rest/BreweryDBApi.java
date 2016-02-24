package com.jdappel.beerinvestigator.rest;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jappel on 2/20/2016.
 */
public interface BreweryDBApi {

    @GET("http://api.brewerydb.com/v2/search?type=beer")
    Observable<BreweryDBResponse<Beer>> getBeers(@Query("q") String query);
}
