package com.jdappel.beerinvestigator.ui.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdappel.beerinvestigator.data.model.Brewery
import javax.inject.Inject
import com.jdappel.beerinvestigator.ui.viewmodel.BeerViewModel
import com.jdappel.beerinvestigator.data.repo.BreweryDBRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Implements [com.jdappel.beerinvestigator.ui.viewmodel.BeerViewModel] to retrieve a list of
 * beers based on the search criteria and populate the view.
 */
internal class BeerViewModelImpl @Inject constructor(private val beerService: BreweryDBRepo) :
    ViewModel(), BeerViewModel {
    private val subject = MutableLiveData<List<Brewery>>()
    override fun subscribe(searchString: Flow<String>, checkbox: Flow<Boolean>) {
        viewModelScope.launch(Dispatchers.IO) {
//            val searchResults: Flow<List<Brewery>> = searchString.flatMapMerge { query: String ->
//                beerService.findBreweries(query)
//                    .map { result ->
//                        when(result) {
//                            is Result.Success -> result.data!!
//                            else -> listOf()
//                        }                        }
//            }
//            val pair = searchResults.combine(checkbox) { first, second -> first to second}
//            pair.collect {
//                    if (it.second) {
//                        subject.postValue(it.first.sorted())
//                    }
//                    subject.postValue(it.first)
//                }
//            }
        }
    }

    override val beers: LiveData<List<Brewery>>
        get() = subject
}