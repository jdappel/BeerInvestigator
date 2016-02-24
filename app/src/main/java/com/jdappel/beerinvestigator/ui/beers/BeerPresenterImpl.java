package com.jdappel.beerinvestigator.ui.beers;

import com.jdappel.beerinvestigator.rest.BreweryDBApi;
import com.jdappel.beerinvestigator.rest.BreweryDBResponse;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jappel on 2/21/2016.
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
                           beers -> {});
    }
}
