package com.jdappel.beerinvestigator.ui.beers

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun provieBeerActivity(): BeerActivity?
}