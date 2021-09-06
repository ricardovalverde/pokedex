package com.example.pokedex.data.response.category

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LanguageName(

    @Json(name = "name")
    val language: String
)
