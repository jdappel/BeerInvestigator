package com.jdappel.beerinvestigator.ui.viewmodel.impl

import javax.inject.Inject
import com.jdappel.beerinvestigator.rest.BreweryDBApi
import com.jdappel.beerinvestigator.ui.viewmodel.BeerViewModel
import io.reactivex.subjects.BehaviorSubject
import com.jdappel.beerinvestigator.rest.Beer
import io.reactivex.disposables.CompositeDisposable
import com.jdappel.beerinvestigator.rest.BreweryDBResponse
import com.google.common.collect.Lists
import io.reactivex.Observable
import java.util.*

/**
 * Implements [com.jdappel.beerinvestigator.ui.viewmodel.BeerViewModel] to retrieve a list of
 * beers based on the search criteria and populate the view.
 */
internal class BeerViewModelImpl @Inject constructor(private val beerService: BreweryDBApi) :
    BeerViewModel {
    private val subject = BehaviorSubject.create<List<Beer>>()
    private val subscriptions = CompositeDisposable()
    override fun subscribe(searchString: Observable<String>, checkbox: Observable<Boolean>) {
        val beers = searchString.flatMap { query: String? ->
            beerService.getBeers(query)
                .filter { obs: BreweryDBResponse<Beer> -> obs.data?.let { it.isNotEmpty() } ?: false }
                .map { t -> t.data }
        }
        val finalList =
            Observable.combineLatest(beers, checkbox, { list: List<Beer>, isChecked: Boolean ->
                if (isChecked) {
                    val newList: List<Beer> = Lists.newArrayList(list)
                    Collections.sort(newList)
                    return@combineLatest newList
                }
                list
            })
        subscriptions.add(finalList.subscribe { t: List<Beer> -> subject.onNext(t) })
    }

    override val beers: Observable<List<Beer>>
        get() = subject

    override fun unsubscribe() {
        if (!subscriptions.isDisposed) subscriptions.clear()
    }
}