package com.jdappel.beerinvestigator.ui.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import javax.inject.Inject
import com.jdappel.beerinvestigator.rest.BreweryDBApi
import com.jdappel.beerinvestigator.ui.viewmodel.BeerViewModel
import io.reactivex.subjects.BehaviorSubject
import com.jdappel.beerinvestigator.rest.Beer
import io.reactivex.disposables.CompositeDisposable
import com.jdappel.beerinvestigator.rest.BreweryDBResponse
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*

/**
 * Implements [com.jdappel.beerinvestigator.ui.viewmodel.BeerViewModel] to retrieve a list of
 * beers based on the search criteria and populate the view.
 */
internal class BeerViewModelImpl @Inject constructor(private val beerService: BreweryDBApi) :
    ViewModel(), BeerViewModel {
    private val subject = MutableLiveData<List<Beer>>()
    override fun subscribe(searchString: Flow<String>, checkbox: Flow<Boolean>) {
        viewModelScope.launch(Dispatchers.IO) {
            val searchResults: Flow<List<Beer>> = searchString.flatMapMerge { query: String? ->
                beerService.getBeers(query)
                    .filter { resp: Response<BreweryDBResponse<Beer>> ->
                        resp.isSuccessful && resp.body()?.data?.isNotEmpty() == true
                    }
                    .map { t -> t.body()!!.data!! }
            }
            val pair = searchResults.combine(checkbox) { first, second -> first to second}
            pair.collect {
                    if (it.second) {
                        subject.postValue(it.first.sorted())
                    }
                    subject.postValue(it.first)
                }
            }
    }

    override val beers: LiveData<List<Beer>>
        get() = subject
}