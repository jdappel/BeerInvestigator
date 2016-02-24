package com.jdappel.beerinvestigator.rest;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jappel on 2/21/2016.
 */
@Module(library = true)
public class BreweryDBServiceModule {

    @Provides
    BreweryDBApi provideBreweryAPI() {
        return BreweryDBService.createBreweryDBService("948fd17f5f5d6560f06d6533f18af582");
    }
}
