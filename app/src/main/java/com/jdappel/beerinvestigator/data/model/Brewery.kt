package com.jdappel.beerinvestigator.data.model

import com.squareup.moshi.Json

data class Brewery(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "name")
    val name: String? = "Test Brewery",
    @field:Json(name = "brewery_type")
    val breweryType: String? = "Test brewery type",
    @field:Json(name = "street")
    val street: String? = "Test brewery street",
    @field:Json(name = "address_2")
    val address2: String? = "Test brewery address2",
    @field:Json(name = "address_3")
    val address3: String? = "Test brewery address3",
    @field:Json(name = "city")
    val city: String? = "Test brewery city",
    @field:Json(name = "state")
    val state: String? = "Test brewery state",
    @field:Json(name = "county_province")
    val countyProvince: String? = "Test brewery contry province",
    @field:Json(name = "postal_code")
    val postalCode: String? = "Test brewery postal code",
    @field:Json(name = "country")
    val country: String? = "Test brewery country",
    @field:Json(name = "longitude")
    val longitude: String? = "Test brewery longitude",
    @field:Json(name = "latitude")
    val latitude: String? = "Test brewery latitude",
    @field:Json(name = "phone")
    val phone: String? = "Test brewery phone",
    @field:Json(name = "website_url")
    val websiteUrl: String? = "Test brewery website",
    @field:Json(name = "updated_at")
    val updatedAt: String? = "Test brewery updated at",
    @field:Json(name = "created_at")
    val createdAt: String? = "Test brewery created at"
)