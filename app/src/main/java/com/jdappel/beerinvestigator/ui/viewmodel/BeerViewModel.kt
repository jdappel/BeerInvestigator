package com.jdappel.beerinvestigator.ui.viewmodel

import androidx.lifecycle.LiveData
import com.jdappel.beerinvestigator.rest.Beer
import io.reactivex.Observable

/**
 * Interface for defining the behavior of the view model.
 */
interface BeerViewModel {
    fun subscribe(searchString: Observable<String>, checkbox: Observable<Boolean>)
    val beers: LiveData<List<Beer>>
}