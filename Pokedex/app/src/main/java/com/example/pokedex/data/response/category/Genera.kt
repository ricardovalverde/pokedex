package com.example.pokedex.data.response.category

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Genera(
    @Json(name = "genus")
    val category: String,

    @Json(name = "language")
    val language: LanguageName
)
