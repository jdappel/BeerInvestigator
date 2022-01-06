package com.jdappel.beerinvestigator.rest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Model class for Beer objects as pulled from BreweryDB.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class Beer @JsonCreator constructor(
    @param:JsonProperty("id") val id: String?,
    @param:JsonProperty("description") val description: String?,
    @param:JsonProperty("name") val name: String?,
    @param:JsonProperty("abv") val abv: Float?,
    @param:JsonProperty(
        "style"
    ) val style: Style?
) : Comparable<Beer> {
    override fun toString(): String {
        return "Beer{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", abv=" + abv +
                ", style=" + style +
                '}'
    }

    override fun compareTo(o: Beer): Int {
        return name?.compareTo(o.name?.let { it } ?: "") ?: -1
    }
}