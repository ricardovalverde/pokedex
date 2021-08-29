package com.example.pokedex.data.response.sprite

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OtherResponse(
    @Json(name = "other")
    val otherResponse: DreamWorldResponse
)
