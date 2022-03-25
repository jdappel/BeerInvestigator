package com.jdappel.beerinvestigator.ui.viewmodel

import androidx.lifecycle.LiveData
import com.jdappel.beerinvestigator.model.Beer
import kotlinx.coroutines.flow.Flow

/**
 * Interface for defining the behavior of the view model.
 */
interface BeerViewModel {
    fun subscribe(searchString: Flow<String>, checkbox: Flow<Boolean>)
    val beers: LiveData<List<Beer>>
}