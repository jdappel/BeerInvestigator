package com.jdappel.beerinvestigator.ui.viewmodel

import androidx.lifecycle.LiveData
import com.jdappel.beerinvestigator.data.model.Brewery
import kotlinx.coroutines.flow.Flow

/**
 * Interface for defining the behavior of the view model.
 */
interface BeerViewModel {
    fun subscribe(searchString: Flow<String>, checkbox: Flow<Boolean>)
    val breweries: LiveData<List<Brewery>>
}