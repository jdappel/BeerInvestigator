package com.jdappel.beerinvestigator.data.repo

import com.jdappel.beerinvestigator.data.model.Brewery
import com.jdappel.beerinvestigator.data.network.Result
import kotlinx.coroutines.flow.Flow

interface BreweryDBRepo {

    fun findBeers(query: String): Flow<Result<List<Brewery>>>
}