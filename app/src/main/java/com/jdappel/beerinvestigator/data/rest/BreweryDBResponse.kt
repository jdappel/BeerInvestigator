package com.jdappel.beerinvestigator.data.rest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Generic Type for returning JSON data from BreweryDB
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class BreweryDBResponse<T> @JsonCreator constructor(
    @param:JsonProperty("success") val status: String? = null, @param:JsonProperty(
        "data"
    ) val data: List<T>?
) {
    override fun toString(): String {
        return "BreweryDBResponse{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}'
    }
}