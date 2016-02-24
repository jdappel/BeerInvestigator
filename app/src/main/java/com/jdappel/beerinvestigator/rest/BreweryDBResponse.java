package com.jdappel.beerinvestigator.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jappel on 2/20/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BreweryDBResponse<T> {

    private final String status;
    private final List<T> data;

    @JsonCreator
    public BreweryDBResponse(@JsonProperty("success") final String status, @JsonProperty("data") final List<T> data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public List<T> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "BreweryDBResponse{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
