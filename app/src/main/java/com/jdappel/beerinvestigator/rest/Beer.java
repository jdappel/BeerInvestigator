package com.jdappel.beerinvestigator.rest;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model class for Beer objects as pulled from BreweryDB.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Beer implements Comparable<Beer> {

    private final String id;
    private final String description;
    private final String name;
    private final float abv;
    private final Style style;

    @JsonCreator
    public Beer(@JsonProperty("id") String id, @JsonProperty("description") String description,
                @JsonProperty("name") String name, @JsonProperty("abv") float abv, @JsonProperty("style") Style style) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.abv = abv;
        this.style = style;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public float getAbv() {
        return abv;
    }

    public Style getStyle() {
        return style;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", abv=" + abv +
                ", style=" + style +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Beer beer = (Beer) o;

        if (Float.compare(beer.abv, abv) != 0) return false;
        if (!id.equals(beer.id)) return false;
        if (!description.equals(beer.description)) return false;
        if (!name.equals(beer.name)) return false;
        return style.equals(beer.style);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (abv != +0.0f ? Float.floatToIntBits(abv) : 0);
        result = 31 * result + style.hashCode();
        return result;
    }

    @Override public int compareTo(@NonNull Beer o) {
        return getName().compareTo(o.getName());
    }
}
