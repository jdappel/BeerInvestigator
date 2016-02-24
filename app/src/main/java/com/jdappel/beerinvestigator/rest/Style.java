package com.jdappel.beerinvestigator.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jappel on 2/20/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Style {

    private final String id;
    private final String description;
    private final float abvMin;
    private final float abvMax;

    @JsonCreator
    public Style(@JsonProperty("id") final String id, @JsonProperty("description") final String description, @JsonProperty("abvMin") final float abvMin, @JsonProperty("abvMax") final float abvMax) {
        this.id = id;
        this.description = description;
        this.abvMin = abvMin;
        this.abvMax = abvMax;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public float getAbvMin() {
        return abvMin;
    }

    public float getAbvMax() {
        return abvMax;
    }

    @Override
    public String toString() {
        return "Style{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", abvMin=" + abvMin +
                ", abvMax=" + abvMax +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Style style = (Style) o;

        if (Float.compare(style.abvMin, abvMin) != 0) return false;
        if (Float.compare(style.abvMax, abvMax) != 0) return false;
        if (!id.equals(style.id)) return false;
        return description.equals(style.description);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (abvMin != +0.0f ? Float.floatToIntBits(abvMin) : 0);
        result = 31 * result + (abvMax != +0.0f ? Float.floatToIntBits(abvMax) : 0);
        return result;
    }
}
