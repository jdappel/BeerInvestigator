package com.jdappel.beerinvestigator.data.network

sealed class APIState<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : APIState<T>(data)
    class Error<T>(message: String? = null) : APIState<T>(message = message)
    class Initial<T>(): APIState<T>()
}