package com.jdappel.beerinvestigator.ui.beers;

/**
 * Created by jappel on 2/21/2016.
 */
public interface BeerPresenter {

    void searchBeers(String beerQuery);
    void setView(BeerView view);
}
