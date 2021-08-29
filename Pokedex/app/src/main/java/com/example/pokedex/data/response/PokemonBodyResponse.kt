package com.example.pokedex.data.response

import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.response.sprite.OtherResponse
import com.example.pokedex.data.response.type.TypeResult
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class PokemonBodyResponse(

    @Json(name = "name")
    val name: String,

    @Json(name = "id")
    val id: Int,

    @Json(name = "sprites")
    val spritesResult: OtherResponse,

    @Json(name = "types")
    val typesResult: List<TypeResult>

) {
    fun getPokemon() = Pokemon(
        name = this.name.uppercase(Locale.getDefault()),
        id = id,
        image = spritesResult.otherResponse.front_image.getImageDream(),
        type1 = typesResult[0].type.getType(),
        type2 = if (typesResult.size > 1) typesResult[1].type.getType() else null
    )
}





