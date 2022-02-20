package com.example.pokedex.data.response

import com.example.pokedex.data.model.Endpoint
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.response.abilities.AbilityResponse
import com.example.pokedex.data.response.flavors.UrlSpecieResponse
import com.example.pokedex.data.response.sprite.OtherResponse
import com.example.pokedex.data.response.stats.BaseResponse
import com.example.pokedex.data.response.types.TypeResult
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonBodyResponse(

    @Json(name = "name")
    val name: String,

    @Json(name = "id")
    val id: Int,

    @Json(name = "height")
    val height: Float,

    @Json(name = "weight")
    val weight: Float,

    @Json(name = "sprites")
    val spritesResult: OtherResponse,

    @Json(name = "types")
    val typesResult: List<TypeResult>,

    @Json(name = "stats")
    val statsResult: List<BaseResponse>,

    @Json(name = "abilities")
    val abilitiesResult: List<AbilityResponse>,

    @Json(name = "species")
    val urlSpecieResult: UrlSpecieResponse,

    ) {
    fun getPokemon() = Pokemon(

        name = this.name.capitalize(),
        id = id,
        weight = (this.weight / 10),
        height = (this.height / 10),
        abilities = this.abilitiesResult,
        image = spritesResult.otherResponse.front_image.getImageDream(),
        urlSpecie = urlSpecieResult.url.removePrefix(Endpoint.BASE_URL),

        type1 = typesResult[0].type.getType(),
        type2 = if (typesResult.size > 1) typesResult[1].type.getType() else null,

        stats = statsResult,
    )
}





