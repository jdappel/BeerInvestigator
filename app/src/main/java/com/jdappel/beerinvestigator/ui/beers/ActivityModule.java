package com.jdappel.beerinvestigator.ui.beers;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract BeerActivity provieBeerActivity();
}
