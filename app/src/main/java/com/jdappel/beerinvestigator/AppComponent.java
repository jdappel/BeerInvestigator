package com.jdappel.beerinvestigator;

import com.jdappel.beerinvestigator.BeerApplication;
import com.jdappel.beerinvestigator.rest.BreweryDBServiceModule;
import com.jdappel.beerinvestigator.ui.beers.ActivityModule;
import com.jdappel.beerinvestigator.ui.viewmodel.impl.BeerModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {BreweryDBServiceModule.class, AndroidInjectionModule.class, ActivityModule.class, BeerModule.class})
interface AppComponent {
    void inject(BeerApplication application);
}
