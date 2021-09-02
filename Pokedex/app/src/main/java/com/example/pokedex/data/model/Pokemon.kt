package com.example.pokedex.data.model

import com.example.pokedex.data.response.stats.BaseResponse

data class Pokemon(
    val name: String,
    val id: Int,
    val image: Sprite,
    var type1: PokemonType,
    var type2: PokemonType?,
    var stats: List<BaseResponse>

)