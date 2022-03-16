package com.jdappel.beerinvestigator.ui.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import com.jdappel.beerinvestigator.rest.BreweryDBApi
import com.jdappel.beerinvestigator.ui.viewmodel.BeerViewModel
import io.reactivex.subjects.BehaviorSubject
import com.jdappel.beerinvestigator.rest.Beer
import io.reactivex.disposables.CompositeDisposable
import com.jdappel.beerinvestigator.rest.BreweryDBResponse
import io.reactivex.Observable
import java.util.*

/**
 * Implements [com.jdappel.beerinvestigator.ui.viewmodel.BeerViewModel] to retrieve a list of
 * beers based on the search criteria and populate the view.
 */
internal class BeerViewModelImpl @Inject constructor(private val beerService: BreweryDBApi) :
    ViewModel(), BeerViewModel {
    private val subject = MutableLiveData<List<Beer>>()
    private val subscriptions = CompositeDisposable()
    override fun subscribe(searchString: Observable<String>, checkbox: Observable<Boolean>) {
        val beers = searchString.flatMap { query: String? ->
            beerService.getBeers(query)
                .filter { obs: BreweryDBResponse<Beer> ->
                    obs.data?.let { it.isNotEmpty() } ?: false
                }
                .map { t -> t.data }
        }
        val finalList =
            Observable.combineLatest(beers, checkbox) { list: List<Beer>, isChecked: Boolean ->
                if (isChecked) {
                    return@combineLatest list.sorted()
                }
                list
            }
        subscriptions.add(finalList.subscribe({ t: List<Beer> -> subject.postValue(t) }, {}))
    }

    override val beers: LiveData<List<Beer>>
        get() = subject

    override fun onCleared() {
        super.onCleared()
        if (!subscriptions.isDisposed) subscriptions.clear()
    }
}