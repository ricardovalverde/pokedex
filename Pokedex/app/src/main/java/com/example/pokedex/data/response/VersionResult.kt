package com.example.pokedex.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VersionResult(
    @Json(name = "name")
    val version: String
)
