package com.jdappel.beerinvestigator.ui.beers;

import com.jdappel.beerinvestigator.rest.Beer;

import java.util.List;

/**
 * Created by jappel on 2/21/2016.
 */
public interface BeerView {

    void showBeers(List<Beer> beers);
}
