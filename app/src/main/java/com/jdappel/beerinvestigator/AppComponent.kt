package com.jdappel.beerinvestigator

import javax.inject.Singleton
import com.jdappel.beerinvestigator.rest.BreweryDBServiceModule
import dagger.android.AndroidInjectionModule
import com.jdappel.beerinvestigator.ui.beers.ActivityModule
import com.jdappel.beerinvestigator.ui.viewmodel.impl.BeerModule
import com.jdappel.beerinvestigator.BeerApplication
import dagger.Component

@Singleton
@Component(modules = [BreweryDBServiceModule::class, AndroidInjectionModule::class, ActivityModule::class, BeerModule::class])
interface AppComponent {
    fun inject(application: BeerApplication?)
}