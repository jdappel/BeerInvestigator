package com.jdappel.beerinvestigator.data.repo

import com.jdappel.beerinvestigator.data.model.Brewery
import com.jdappel.beerinvestigator.data.network.APIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface BreweryDBRepo {

    val brewerySearchResults : Flow<APIState<List<Brewery>>>

    suspend fun initialize()

    suspend fun findBreweries(query: String)
}