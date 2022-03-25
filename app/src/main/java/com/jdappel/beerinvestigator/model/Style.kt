package com.jdappel.beerinvestigator.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Model class for style information related to a beer.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class Style @JsonCreator constructor(
    @param:JsonProperty("id") val id: String, @param:JsonProperty(
        "description"
    ) val description: String, @param:JsonProperty("abvMin") val abvMin: Float, @param:JsonProperty(
        "abvMax"
    ) val abvMax: Float
)