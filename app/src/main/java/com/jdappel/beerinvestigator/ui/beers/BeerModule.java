package com.jdappel.beerinvestigator.ui.beers;

import com.jdappel.beerinvestigator.rest.BreweryDBApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jappel on 2/21/2016.
 */
@Module(injects = BeerActivity.class, complete = false)
public class BeerModule {

    @Provides
    BeerPresenter provideBeerPresenter(final BreweryDBApi service) {
        return new BeerPresenterImpl(service);
    }
}
