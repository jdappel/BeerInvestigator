package com.jdappel.beerinvestigator.ui.breweries

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jdappel.beerinvestigator.ui.viewmodel.impl.BreweryViewModelImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun BreweryScreen(
    viewModel: BreweryViewModelImpl = viewModel()
) {
    /*Scaffold(
        topBar = {
            BreweryTopBar()
        }
    ) { padding ->

    }*/
}
