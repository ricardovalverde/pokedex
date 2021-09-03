package com.example.pokedex.presentation.pokemons

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.data.model.ApiService
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.response.PokemonBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonsViewModel : ViewModel() {
    val pokemonsLiveData: MutableLiveData<List<Pokemon>> = MutableLiveData()
    val viewFlipper: MutableLiveData<Int> = MutableLiveData()

    fun getPokemons() {
        val listPokemons: MutableList<Pokemon> = mutableListOf()

        for (id in 500..700) {
            ApiService.service.getPokemonList(id).enqueue(object : Callback<PokemonBodyResponse> {
                override fun onResponse(
                    call: Call<PokemonBodyResponse>,
                    response: Response<PokemonBodyResponse>

                ) {
                    when {
                        response.isSuccessful -> {
                            response.body()?.let { pokemonBodyResponse ->
                                val pokemon = pokemonBodyResponse.getPokemon()
                                listPokemons.add(pokemon)
                            }
                            viewFlipper.value = VIEW_FLIPPER_LINEAR_LAYOUT
                            pokemonsLiveData.value = listPokemons.sortedBy { it.id }
                        }
                    }
                }

                override fun onFailure(call: Call<PokemonBodyResponse>, t: Throwable) {
                    viewFlipper.value = VIEW_FLIPPER_ERROR_IMAGE
                    Log.i("Failure", t.message.toString())
                }
            })
        }
    }

    companion object {
        private const val VIEW_FLIPPER_ERROR_IMAGE = 1
        private const val VIEW_FLIPPER_LINEAR_LAYOUT = 2
    }
}

