package com.jdappel.beerinvestigator.ui.viewmodel.impl;

import com.jdappel.beerinvestigator.rest.BreweryDBApi;
import com.jdappel.beerinvestigator.ui.beers.BeerActivity;
import com.jdappel.beerinvestigator.ui.viewmodel.BeerViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Module for constructing a {@link BeerViewModel} for injection into {@link BeerActivity}
 */
@Module(injects = BeerActivity.class, complete = false)
public class BeerModule {

    @Provides BeerViewModel provideBeerPresenter(final BreweryDBApi service) {
        return new BeerViewModelImpl(service);
    }
}
