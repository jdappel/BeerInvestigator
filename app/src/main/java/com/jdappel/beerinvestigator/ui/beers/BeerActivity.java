package com.jdappel.beerinvestigator.ui.beers;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;

import androidx.databinding.DataBindingUtil;

import com.jakewharton.rxbinding2.widget.RxCompoundButton;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jdappel.beerinvestigator.BeerApplication;
import com.jdappel.beerinvestigator.R;
import com.jdappel.beerinvestigator.databinding.BeerInvestigatorLayoutBinding;
import com.jdappel.beerinvestigator.ui.viewmodel.BeerViewModel;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.DaggerActivity;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


/**
 * Main activity class that controls access to the UI components for this sample application.  Holds
 * a reference to a {@link com.jdappel.beerinvestigator.ui.viewmodel.BeerViewModel} that is injected
 * to access beer information.
 */
public class BeerActivity extends Activity {

    CheckBox sortedCheckedBox;

    @Inject BeerViewModel beerViewModel;

    private ExpandableListAdapter listAdapter;
    private Disposable subscription;

    @Override public void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        BeerInvestigatorLayoutBinding binding = DataBindingUtil.setContentView(this, R.layout.beer_investigator_layout);

        BeerApplication application = (BeerApplication) getApplication();

        listAdapter = new ExpandableListAdapter(getLayoutInflater());
        listAdapter.setBeers(Collections.emptyList());
        binding.expandableListView.setAdapter(listAdapter);

        Observable<String> searchString = RxTextView.textChangeEvents(binding.searchView)
                .filter(event -> !TextUtils.isEmpty(event.text().toString()))
                .map(event -> event.text().toString())
                .debounce(200, TimeUnit.MILLISECONDS)
                .subscribeOn(AndroidSchedulers.mainThread());

        Observable<Boolean> checkBox = RxCompoundButton.checkedChanges(binding.checkBox)
                .debounce(200, TimeUnit.MILLISECONDS)
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
            subscription.dispose();
        }
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        beerViewModel.unsubscribe();
    }
}
