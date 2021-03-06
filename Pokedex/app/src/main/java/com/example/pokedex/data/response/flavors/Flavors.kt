package com.example.pokedex.data.response.flavors

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Flavors(
    @Json(name = "flavor_text")
    val flavorText: String,

    @Json(name = "language")
    val languagesResponse: LanguageResult
)
