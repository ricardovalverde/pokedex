package com.example.pokedex.data.response.abilities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AbilityResponse(

    @Json(name = "ability")
    val nameAbility: NameAbilityResponse
)
