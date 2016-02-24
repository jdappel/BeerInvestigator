package com.jdappel.beerinvestigator.ui.beers;

import android.util.Log;

import com.jdappel.beerinvestigator.rest.BreweryDBApi;
import com.jdappel.beerinvestigator.rest.BreweryDBResponse;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Implements {@link BeerPresenter} to retrieve a list of beers based on the search criteria
 * and populate the view.
 */
class BeerPresenterImpl implements BeerPresenter {

    private final BreweryDBApi beerService;
    private BeerView beerView;

    BeerPresenterImpl(final BreweryDBApi beerService) {
        this.beerService = beerService;
    }

    @Override
    public void setView(BeerView view) {
        beerView = view;
    }

    @Override
    public void searchBeers(final String query) {
        beerService.getBeers(query)
                .filter(obs -> obs.getData() != null && !obs.getData().isEmpty())
                .map(BreweryDBResponse::getData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(beers -> beerView.showBeers(beers),
                        error -> Log.e(getClass().getName(), error.getMessage()));
    }
}
