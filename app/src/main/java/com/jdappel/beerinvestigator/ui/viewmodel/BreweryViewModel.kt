package com.jdappel.beerinvestigator.ui.viewmodel

import androidx.lifecycle.LiveData
import com.jdappel.beerinvestigator.data.model.Brewery
import com.jdappel.beerinvestigator.ui.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

/**
 * Interface for defining the behavior of the view model.
 */
interface BreweryViewModel {
    fun subscribe(searchString: Flow<String>, checkbox: Flow<Boolean>)
    val breweries: StateFlow<UIState<List<Brewery>>>
}