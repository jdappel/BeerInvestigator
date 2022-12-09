package com.jdappel.beerinvestigator.ui.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdappel.beerinvestigator.data.model.Brewery
import com.jdappel.beerinvestigator.data.network.APIState
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

    private val _breweries = MutableLiveData<List<Brewery>>()
    override val breweries: LiveData<List<Brewery>> = _breweries

    init {
        viewModelScope.launch(Dispatchers.IO) {
            beerService.brewerySearchResults.collect { searchState ->
                when (searchState) {
                    is APIState.Success -> _breweries.value = searchState.data
                    else -> {}
                }
            }
        }
    }

    override fun subscribe(searchString: Flow<String>, checkbox: Flow<Boolean>) {
        viewModelScope.launch(Dispatchers.IO) {
            beerService.findBreweries(searchString.first())
        }
    }
}