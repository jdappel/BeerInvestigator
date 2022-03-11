package com.jdappel.beerinvestigator.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jdappel.beerinvestigator.ui.viewmodel.impl.BeerViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(BeerViewModelImpl::class)
    internal abstract fun vm(viewModel: BeerViewModelImpl): ViewModel
}