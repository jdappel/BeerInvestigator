package com.jdappel.beerinvestigator.ui.beers;

/**
 * Interface for defining the behavior of the presenter within this view.
 */
public interface BeerPresenter {

    void searchBeers(String beerQuery);

    void setView(BeerView view);
}
