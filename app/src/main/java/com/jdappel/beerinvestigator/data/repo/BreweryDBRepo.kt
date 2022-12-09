package com.jdappel.beerinvestigator.data.repo

import com.jdappel.beerinvestigator.data.model.Brewery
import com.jdappel.beerinvestigator.data.network.APIState
import kotlinx.coroutines.flow.StateFlow

interface BreweryDBRepo {

    val brewerySearchResults : StateFlow<APIState<List<Brewery>>>

    suspend fun findBreweries(query: String)
}