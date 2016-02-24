package com.jdappel.beerinvestigator.rest;

import dagger.Module;
import dagger.Provides;

/**
 * {@code Dagger} module for constructing a REST client with {@code Retrofit}
 */
@Module(library = true)
public class BreweryDBServiceModule {

    @Provides
    BreweryDBApi provideBreweryAPI() {
        return BreweryDBService.createBreweryDBService("948fd17f5f5d6560f06d6533f18af582");
    }
}
