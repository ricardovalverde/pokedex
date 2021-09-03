package com.example.pokedex.data.model

import com.example.pokedex.data.response.abilities.AbilityResponse
import com.example.pokedex.data.response.stats.BaseResponse

data class Pokemon(
    val name: String,
    val id: Int,
    val weight: Float,
    val height: Float,
    val image: Sprite,
    val type1: PokemonType,
    val type2: PokemonType?,
    val stats: List<BaseResponse>,
    val abilities: List<AbilityResponse>
)