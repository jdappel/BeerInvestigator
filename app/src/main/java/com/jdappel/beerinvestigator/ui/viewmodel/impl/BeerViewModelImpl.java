package com.jdappel.beerinvestigator.ui.viewmodel.impl;

import com.jdappel.beerinvestigator.rest.Beer;
import com.jdappel.beerinvestigator.rest.BreweryDBApi;
import com.jdappel.beerinvestigator.rest.BreweryDBResponse;
import com.jdappel.beerinvestigator.ui.viewmodel.BeerViewModel;

import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Implements {@link com.jdappel.beerinvestigator.ui.viewmodel.BeerViewModel} to retrieve a list of
 * beers based on the search criteria and populate the view.
 */
class BeerViewModelImpl implements BeerViewModel {

    private final BreweryDBApi beerService;
    private final BehaviorSubject<List<Beer>> subject = BehaviorSubject.create();
    private final CompositeSubscription subscriptions = new CompositeSubscription();

    public BeerViewModelImpl(final BreweryDBApi beerService) {
        this.beerService = beerService;
    }

    @Override public void subscribe(Observable<String> searchString, Observable<Boolean> checkbox) {
        Observable<List<Beer>> beers = searchString.flatMap(query -> beerService.getBeers(query)
                .filter(obs -> obs.getData() != null && !obs.getData().isEmpty())
                .map(BreweryDBResponse::getData).subscribeOn(Schedulers.io()));

        Observable<List<Beer>> finalList = checkbox.flatMap(isChecked ->
            isChecked ? beers.sorted() : beers
        );
        subscriptions.add(finalList.subscribe(subject::onNext));
    }

    @Override public Observable<List<Beer>> getBeers() {
        return subject.asObservable();
    }

    @Override public void unsubscribe() {
        if (subscriptions.hasSubscriptions())
            subscriptions.clear();
    }
}
