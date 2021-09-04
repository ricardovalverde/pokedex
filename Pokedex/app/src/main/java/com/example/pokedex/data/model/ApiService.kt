package com.example.pokedex.data.model

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiService {
    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Endpoint.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val service: PokemonService = initRetrofit().create(PokemonService::class.java)
}