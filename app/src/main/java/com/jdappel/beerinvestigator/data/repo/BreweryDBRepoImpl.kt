package com.jdappel.beerinvestigator.data.repo

import com.jdappel.beerinvestigator.data.model.Beer
import com.jdappel.beerinvestigator.data.network.BreweryDBApi
import com.jdappel.beerinvestigator.data.network.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

internal class BreweryDBRepoImpl @Inject constructor(private val beerService: BreweryDBApi) :
    BreweryDBRepo {
    override fun findBeers(query: String): Flow<Result<List<Beer>>> {
        return flow {
            when (val result = safeApiCall { beerService.getBeers(query) }) {
                is Result.Success -> {
                    result.data?.let { apiBinding ->
                        emit(Result.Success(apiBinding.data ?: listOf()))
                    }
                }
                else -> emit(error<List<Beer>>("API error"))
            }
        }.flowOn(Dispatchers.IO)
    }


    private suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Result<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return Result.Success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String): Result<T> =
        Result.Error("Api call failed $errorMessage")


}