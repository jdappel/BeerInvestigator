package com.jdappel.beerinvestigator.ui.viewmodel;

import com.jdappel.beerinvestigator.rest.Beer;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface for defining the behavior of the view model.
 */
public interface BeerViewModel {

    void subscribe(Observable<String> searchString, Observable<Boolean> checkbox);

    Observable<List<Beer>> getBeers();

    void unsubscribe();
}
