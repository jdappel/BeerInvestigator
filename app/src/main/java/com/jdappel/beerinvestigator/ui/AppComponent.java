package com.jdappel.beerinvestigator.ui;

import com.jdappel.beerinvestigator.BeerApplication;
import com.jdappel.beerinvestigator.rest.BreweryDBServiceModule;
import com.jdappel.beerinvestigator.ui.beers.ActivityModule;
import com.jdappel.beerinvestigator.ui.viewmodel.impl.BeerModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {BreweryDBServiceModule.class, AndroidInjectionModule.class, ActivityModule.class, BeerModule.class})
public interface AppComponent extends AndroidInjector<BeerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(BeerApplication app);
        AppComponent build();
    }
}
