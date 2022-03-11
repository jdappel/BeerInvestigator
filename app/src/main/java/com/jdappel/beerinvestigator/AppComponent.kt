package com.jdappel.beerinvestigator

import com.jdappel.beerinvestigator.di.ViewModelModule
import javax.inject.Singleton
import com.jdappel.beerinvestigator.rest.BreweryDBServiceModule
import dagger.android.AndroidInjectionModule
import com.jdappel.beerinvestigator.ui.beers.ActivityModule
import com.jdappel.beerinvestigator.ui.viewmodel.impl.BeerModule
import dagger.Component

@Singleton
@Component(modules = [ViewModelModule::class, BreweryDBServiceModule::class, AndroidInjectionModule::class, ActivityModule::class, BeerModule::class])
interface AppComponent {
    fun inject(application: BeerApplication?)
}