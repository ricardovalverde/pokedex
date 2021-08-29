package com.example.pokedex.data.model

data class Pokemon(
    val name: String,
    val id: Int,
    val image: Sprite,
    var type1: PokemonType,
    var type2: PokemonType?
)