package com.jdappel.beerinvestigator.ui.beers;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jdappel.beerinvestigator.BeerApplication;
import com.jdappel.beerinvestigator.R;
import com.jdappel.beerinvestigator.rest.Beer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Main activity class that controls access to the UI components for this sample application.  Holds
 * a reference to a {@link BeerPresenter} that is injected to access beer information.
 */
public class BeerActivity extends Activity implements BeerView {

    @Bind(R.id.searchView)
    EditText searchView;
    @Bind(R.id.listView)
    RecyclerView listView;

    @Inject
    BeerPresenter beerPresenter;

    private BeerListAdapter listAdapter;
    private Subscription subscription;
    private RecyclerView.LayoutManager listLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_investigator_layout);
        ButterKnife.bind(this);

        BeerApplication application = (BeerApplication) getApplication();
        application.getObjectGraph().inject(this);


        listLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(listLayoutManager);
        listAdapter = new BeerListAdapter(new ArrayList<Beer>(), getLayoutInflater());
        listView.setAdapter(listAdapter);
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
                .onBackpressureLatest()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(query -> beerPresenter.searchBeers(query),
                        error -> Log.e(getClass().getName(), error.getMessage()));
    }

    @Override
    public void showBeers(List<Beer> beers) {
        listAdapter.setBeers(beers);
    }
}
