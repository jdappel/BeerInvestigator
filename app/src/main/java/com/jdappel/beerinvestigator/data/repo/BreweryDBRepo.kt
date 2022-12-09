package com.jdappel.beerinvestigator.data.repo

import com.jdappel.beerinvestigator.data.model.Beer
import com.jdappel.beerinvestigator.data.rest.Result
import kotlinx.coroutines.flow.Flow

interface BreweryDBRepo {

   fun findBeers(query: String): Flow<Result<List<Beer>>>
}