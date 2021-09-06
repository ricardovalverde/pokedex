package com.example.pokedex.data.model


import com.example.pokedex.data.response.PokemonBodyResponse
import com.example.pokedex.data.response.PokemonSpeciesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
    @GET("pokemon/{id}")
    fun getPokemonList(@Path("id") id: Int): Call<PokemonBodyResponse>

    @GET("{url}")
    fun getSpeciesList(@Path("url") url: String): Call<PokemonSpeciesResponse>
}
