package com.example.pokedex.data.response

import com.example.pokedex.data.model.Specie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonSpeciesResponse(

    @Json(name = "flavor_text_entries")
    val flavorResult: List<Flavors>,

    ) {
    fun getSpecie() = Specie(
        specie = flavorResult
    )
}