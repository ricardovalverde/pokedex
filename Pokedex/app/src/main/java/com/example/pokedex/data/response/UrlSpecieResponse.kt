package com.example.pokedex.data.response

import com.example.pokedex.data.model.Specie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UrlSpecieResponse(
    @Json(name = "url")
    val url: String
)
