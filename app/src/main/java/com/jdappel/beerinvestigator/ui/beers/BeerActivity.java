package com.jdappel.beerinvestigator.ui.beers;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.SearchView;

import com.jakewharton.rxbinding.widget.RxSearchView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.jdappel.beerinvestigator.BeerApplication;
import com.jdappel.beerinvestigator.R;
import com.jdappel.beerinvestigator.rest.Beer;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jappel on 2/21/2016.
 */
public class BeerActivity extends Activity implements BeerView {

    @Bind(R.id.searchView) EditText searchView;
    @Bind(R.id.expandableListView) ExpandableListView expandableListView;

    @Inject BeerPresenter beerPresenter;

    private ExpandableListAdapter listAdapter;
    private Subscription subscription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_investigator_layout);
        ButterKnife.bind(this);

        BeerApplication application = (BeerApplication) getApplication();
        application.getObjectGraph().inject(this);
        listAdapter = new ExpandableListAdapter(getLayoutInflater());
        listAdapter.setBeers(Collections.emptyList());
        expandableListView.setAdapter(listAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (subscription != null)
            subscription.unsubscribe();
    }

    @Override
    protected void onStart() {
        super.onStart();
        beerPresenter.setView(this);
        subscription = RxTextView.textChangeEvents(searchView)
                .filter(event -> !TextUtils.isEmpty(event.text().toString()))
                .map(event -> event.text().toString())
                .debounce(200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(query -> beerPresenter.searchBeers(query),
                           error -> Log.e(getClass().getName(),error.getMessage()));
    }

    @Override
    public void showBeers(List<Beer> beers) {
        listAdapter.setBeers(beers);
    }
}
