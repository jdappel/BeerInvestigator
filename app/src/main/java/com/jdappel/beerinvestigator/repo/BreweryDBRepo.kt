package com.jdappel.beerinvestigator.repo

import com.jdappel.beerinvestigator.model.Beer
import com.jdappel.beerinvestigator.rest.Result
import kotlinx.coroutines.flow.Flow

interface BreweryDBRepo {

   suspend fun findBeers(query: String): Flow<Result<List<Beer>>>
}