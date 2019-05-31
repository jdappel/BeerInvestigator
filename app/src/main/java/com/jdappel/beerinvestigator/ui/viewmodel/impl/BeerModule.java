package com.jdappel.beerinvestigator.ui.viewmodel.impl;

import com.jdappel.beerinvestigator.rest.BreweryDBApi;
import com.jdappel.beerinvestigator.ui.viewmodel.BeerViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class BeerModule {

    @Provides
    BeerViewModel provideBeerViewModel(BreweryDBApi service) {
        return new BeerViewModelImpl(service);
    }
}
