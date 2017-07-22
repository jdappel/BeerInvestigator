package com.jdappel.beerinvestigator.ui.beers;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;

import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jdappel.beerinvestigator.BeerApplication;
import com.jdappel.beerinvestigator.R;
import com.jdappel.beerinvestigator.ui.viewmodel.BeerViewModel;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Main activity class that controls access to the UI components for this sample application.  Holds
 * a reference to a {@link com.jdappel.beerinvestigator.ui.viewmodel.BeerViewModel} that is injected
 * to access beer information.
 */
public class BeerActivity extends Activity {

    @BindView(R.id.searchView) EditText searchView;
    @BindView(R.id.expandableListView) ExpandableListView expandableListView;
    @BindView(R.id.checkBox) CheckBox sortedCheckedBox;

    @Inject BeerViewModel beerViewModel;

    private ExpandableListAdapter listAdapter;
    private Subscription subscription;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_investigator_layout);
        ButterKnife.bind(this);

        BeerApplication application = (BeerApplication) getApplication();
        application.getObjectGraph().inject(this);
        listAdapter = new ExpandableListAdapter(getLayoutInflater());
        listAdapter.setBeers(Collections.emptyList());
        expandableListView.setAdapter(listAdapter);

        Observable<String> searchString = RxTextView.textChangeEvents(searchView)
                .filter(event -> !TextUtils.isEmpty(event.text().toString()))
                .map(event -> event.text().toString())
                .debounce(200, TimeUnit.MILLISECONDS)
                .onBackpressureLatest()
                .subscribeOn(AndroidSchedulers.mainThread());

        Observable<Boolean> checkBox = RxCompoundButton.checkedChanges(sortedCheckedBox)
                .debounce(200, TimeUnit.MILLISECONDS)
                .onBackpressureLatest()
                .subscribeOn(AndroidSchedulers.mainThread());

        beerViewModel.subscribe(searchString, checkBox);
    }

    @Override protected void onResume() {
        super.onResume();
        subscription = beerViewModel.getBeers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(beers -> listAdapter.setBeers(beers),
                        throwable -> Log.e("error", throwable.getMessage()));
    }

    @Override protected void onPause() {
        super.onPause();
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        beerViewModel.unsubscribe();
    }
}
