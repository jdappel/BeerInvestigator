package com.jdappel.beerinvestigator.ui.viewmodel

import com.jdappel.beerinvestigator.rest.Beer
import io.reactivex.Observable

/**
 * Interface for defining the behavior of the view model.
 */
interface BeerViewModel {
    fun subscribe(searchString: Observable<String>, checkbox: Observable<Boolean>)
    val beers: Observable<List<Beer>>
    fun unsubscribe()
}