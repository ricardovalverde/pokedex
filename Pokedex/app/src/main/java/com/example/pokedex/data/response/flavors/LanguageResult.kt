package com.example.pokedex.data.response.flavors

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class LanguageResult(
    @Json(name = "name")
    val language: String
)
