package com.jdappel.beerinvestigator.rest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Model class for style information related to a beer.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class Style @JsonCreator constructor(
    @param:JsonProperty("id") val id: String, @param:JsonProperty(
        "description"
    ) val description: String, @param:JsonProperty("abvMin") val abvMin: Float, @param:JsonProperty(
        "abvMax"
    ) val abvMax: Float
) {
    override fun toString(): String {
        return "Style{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", abvMin=" + abvMin +
                ", abvMax=" + abvMax +
                '}'
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val style = o as Style
        if (java.lang.Float.compare(style.abvMin, abvMin) != 0) return false
        if (java.lang.Float.compare(style.abvMax, abvMax) != 0) return false
        return if (id != style.id) false else description == style.description
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + if (abvMin != +0.0f) java.lang.Float.floatToIntBits(abvMin) else 0
        result = 31 * result + if (abvMax != +0.0f) java.lang.Float.floatToIntBits(abvMax) else 0
        return result
    }
}