package com.jdappel.beerinvestigator.ui.viewmodel.impl;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.jdappel.beerinvestigator.rest.Beer;
import com.jdappel.beerinvestigator.rest.BreweryDBApi;
import com.jdappel.beerinvestigator.rest.BreweryDBResponse;
import com.jdappel.beerinvestigator.rest.Style;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subscribers.TestSubscriber;

@RunWith(MockitoJUnitRunner.class)
public class BeerViewModelImplTest {

    private BreweryDBApi api;
    private BeerViewModelImpl impl;
    private List<Beer> beers = new ArrayList<Beer>() {{

        add(new Beer("1", "Great IPA", "Two Hearted Ale", 6.7f, new Style("1", "IPA", 6, 7.8f)));
        add(new Beer("2", "Another Great IPA", "90 minute IPA", 7.1f,
                new Style("1", "IPA", 6, 7.8f)));

    }};

    private List<Beer> sortedBeers = new ArrayList<>(beers);

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Before public void setup() {

        api = Mockito.mock(BreweryDBApi.class);
        BreweryDBResponse<Beer> response = new BreweryDBResponse<>("success", beers);
        Mockito.when(api.getBeers(Mockito.anyString())).thenReturn(Observable.just(response));
        impl = new BeerViewModelImpl(api);
    }

    @Test
    public void testInitialResponse() {
        Observable<String> search = Observable.just("IP");
        Observable<Boolean> checked = Observable.just(false);
        Observer<List<Beer>> subscriber = Mockito.mock(Observer.class);
        impl.subscribe(search, checked);
        impl.getBeers().observeForever(subscriber);
        Mockito.verify(subscriber).onChanged(beers);
    }

    @Test
    public void testWithSorting() {
        Observable<String> search = Observable.just("IP");
        Observable<Boolean> checked = Observable.just(false, true);
        Observer<List<Beer>> subscriber = Mockito.mock(Observer.class);
        impl.subscribe(search, checked);
        impl.getBeers().observeForever(subscriber);
        Collections.sort(sortedBeers);
        Mockito.verify(subscriber).onChanged(sortedBeers);
    }
}
