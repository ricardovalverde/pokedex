package com.example.pokedex.data.response.type

import com.example.pokedex.data.model.PokemonType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class TypeResponse(
    @Json(name = "name")
    val typeName: String
) {
    fun getType() = PokemonType(
        type = this.typeName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    )
}
