package com.example.pokedex.data.response.types

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TypeResult(
    @Json(name = "type")
    val type: TypeResponse
)
