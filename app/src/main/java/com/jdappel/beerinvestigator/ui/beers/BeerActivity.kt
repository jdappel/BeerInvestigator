package com.jdappel.beerinvestigator.ui.beers

import android.app.Activity
import android.widget.CheckBox
import javax.inject.Inject
import com.jdappel.beerinvestigator.ui.viewmodel.BeerViewModel
import io.reactivex.disposables.Disposable
import android.os.Bundle
import dagger.android.AndroidInjection
import androidx.databinding.DataBindingUtil
import com.jdappel.beerinvestigator.R
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import android.text.TextUtils
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import com.jakewharton.rxbinding2.widget.RxCompoundButton
import com.jdappel.beerinvestigator.databinding.BeerInvestigatorLayoutBinding
import java.util.concurrent.TimeUnit

/**
 * Main activity class that controls access to the UI components for this sample application.  Holds
 * a reference to a [com.jdappel.beerinvestigator.ui.viewmodel.BeerViewModel] that is injected
 * to access beer information.
 */
class BeerActivity : Activity() {
    var sortedCheckedBox: CheckBox? = null

    @JvmField
    @Inject
    var beerViewModel: BeerViewModel? = null
    private var listAdapter: ExpandableListAdapter? = null
    private var subscription: Disposable? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        val binding: BeerInvestigatorLayoutBinding =
            DataBindingUtil.setContentView(this, R.layout.beer_investigator_layout)
        listAdapter = ExpandableListAdapter(layoutInflater)
        listAdapter!!.setBeers(emptyList())
        binding.expandableListView.setAdapter(listAdapter)
        val searchString = RxTextView.textChangeEvents(binding.searchView)
            .filter { event: TextViewTextChangeEvent ->
                !TextUtils.isEmpty(
                    event.text().toString()
                )
            }
            .map { event: TextViewTextChangeEvent -> event.text().toString() }
            .debounce(200, TimeUnit.MILLISECONDS)
            .subscribeOn(AndroidSchedulers.mainThread())
        val checkBox = RxCompoundButton.checkedChanges(binding.checkBox)
            .debounce(200, TimeUnit.MILLISECONDS)
            .subscribeOn(AndroidSchedulers.mainThread())
        beerViewModel!!.subscribe(searchString, checkBox)
    }

    override fun onResume() {
        super.onResume()
        subscription = beerViewModel!!.beers
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { beers -> listAdapter!!.setBeers(beers) }
            ) { throwable: Throwable -> Log.e("error", throwable.message!!) }
    }

    override fun onPause() {
        super.onPause()
        if (subscription != null) {
            subscription!!.dispose()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        beerViewModel!!.unsubscribe()
    }
}