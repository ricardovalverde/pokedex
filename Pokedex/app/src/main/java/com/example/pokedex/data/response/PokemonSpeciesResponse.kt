package com.example.pokedex.data.response

import com.example.pokedex.data.model.Category
import com.example.pokedex.data.model.Specie
import com.example.pokedex.data.response.category.Genera
import com.example.pokedex.data.response.flavors.Flavors
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PokemonSpeciesResponse(

    @Json(name = "flavor_text_entries")
    val flavorResult: List<Flavors>,

    @Json(name = "genera")
    val generaResult: List<Genera>

) {
    fun getSpecie() = Specie(
        FlavorsList = flavorResult
    )

    fun getCategory() = Category(
        Category = generaResult
    )
}