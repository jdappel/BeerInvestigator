package com.jdappel.beerinvestigator.ui.viewmodel.impl

import com.jdappel.beerinvestigator.repo.BreweryDBRepo
import com.jdappel.beerinvestigator.rest.BreweryDBApi
import com.jdappel.beerinvestigator.ui.viewmodel.BeerViewModel

import dagger.Module
import dagger.Provides

@Module
class BeerModule {
    @Provides
    fun provideBeerViewModel(service: BreweryDBRepo): BeerViewModel {
        return BeerViewModelImpl(service)
    }
}