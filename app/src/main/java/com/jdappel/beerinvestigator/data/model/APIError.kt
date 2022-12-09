package com.jdappel.beerinvestigator.data.model

data class APIError(
    override val message: String? = null,
    val throwable: Throwable? = null,
) : Exception(throwable)