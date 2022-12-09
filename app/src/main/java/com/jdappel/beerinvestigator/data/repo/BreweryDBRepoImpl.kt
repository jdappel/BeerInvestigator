package com.jdappel.beerinvestigator.data.repo

import com.jdappel.beerinvestigator.data.model.Brewery
import com.jdappel.beerinvestigator.data.network.BreweryDBApi
import com.jdappel.beerinvestigator.data.network.APIState
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

internal class BreweryDBRepoImpl @Inject constructor(
    private val beerService: BreweryDBApi
) : BreweryDBRepo {

    private val state = MutableStateFlow<APIState<List<Brewery>>>(APIState.Initial())
    override val brewerySearchResults
        get() = state

    override suspend fun findBreweries(query: String) {
        try {
            val result = beerService.getBeers(query)
            if (result.isSuccessful) {
                val body = result.body()
                body?.let {
                    state.emit(APIState.Success(it))
                }
            }

        } catch (e: Exception) {
            if (e is CancellationException) {
                throw e
            }
            state.emit(APIState.Error(e.message))
        }
    }
}