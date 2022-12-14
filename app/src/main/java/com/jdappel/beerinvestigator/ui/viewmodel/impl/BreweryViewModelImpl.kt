package com.jdappel.beerinvestigator.ui.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdappel.beerinvestigator.data.model.Brewery
import com.jdappel.beerinvestigator.data.network.APIState
import javax.inject.Inject
import com.jdappel.beerinvestigator.ui.viewmodel.BreweryViewModel
import com.jdappel.beerinvestigator.data.repo.BreweryDBRepo
import com.jdappel.beerinvestigator.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Implements [com.jdappel.beerinvestigator.ui.viewmodel.BreweryViewModel] to retrieve a list of
 * beers based on the search criteria and populate the view.
 */
@HiltViewModel
class BreweryViewModelImpl @Inject constructor(private val beerService: BreweryDBRepo) :
    ViewModel(), BreweryViewModel {

    private val _breweries = MutableStateFlow<UIState<List<Brewery>>>(UIState.Unknown())
    override val breweries: StateFlow<UIState<List<Brewery>>> = _breweries

    init {
        viewModelScope.launch {
            beerService.initialize()
        }
        viewModelScope.launch {
            beerService.brewerySearchResults.collect { searchState ->
                when (searchState) {
                    is APIState.Success -> _breweries.value = UIState.Success(searchState.data?.let { it } ?: emptyList())
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