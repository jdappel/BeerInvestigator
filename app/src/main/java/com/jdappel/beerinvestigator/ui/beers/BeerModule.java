package com.jdappel.beerinvestigator.ui.beers;

import com.jdappel.beerinvestigator.rest.BreweryDBApi;

import dagger.Module;
import dagger.Provides;

/**
 * Module for constructing a {@link BeerPresenter} for injection into {@link BeerActivity}
 */
@Module(injects = BeerActivity.class, complete = false)
public class BeerModule {

    @Provides
    BeerPresenter provideBeerPresenter(final BreweryDBApi service) {
        return new BeerPresenterImpl(service);
    }
}
