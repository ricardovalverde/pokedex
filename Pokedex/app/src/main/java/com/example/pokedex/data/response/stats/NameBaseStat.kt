package com.example.pokedex.data.response.stats

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NameBaseStat(
    @Json(name = "name")
    val nameBaseStat: String
)
