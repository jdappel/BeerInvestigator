package com.jdappel.beerinvestigator.ui.beers;

import com.jdappel.beerinvestigator.rest.Beer;

import java.util.List;

/**
 * Defines the behavior for interacting with the view vis-a-vis the presenter.
 */
interface BeerView {

    void showBeers(List<Beer> beers);
}
