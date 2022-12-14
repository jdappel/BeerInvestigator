package com.jdappel.beerinvestigator.data.repo

import com.jdappel.beerinvestigator.data.model.Brewery
import com.jdappel.beerinvestigator.data.network.BreweryDBApi
import com.jdappel.beerinvestigator.data.network.APIState
import com.jdappel.beerinvestigator.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.cancellation.CancellationException

@Singleton
internal class BreweryDBRepoImpl @Inject constructor(
    private val beerService: BreweryDBApi,
    @IODispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : BreweryDBRepo {

    private val state = MutableStateFlow<APIState<List<Brewery>>>(APIState.Initial())
    override val brewerySearchResults
        get() = state

    override suspend fun initialize() {
        withContext(dispatcher) {
            executeApiCall { beerService.getBreweries() }
        }
    }

    override suspend fun findBreweries(query: String) {
        executeApiCall {  beerService.findBreweries(query) }
    }

    private suspend fun executeApiCall(apiCall: suspend () -> Response<List<Brewery>>) {
        try {
            val result = apiCall.invoke()
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