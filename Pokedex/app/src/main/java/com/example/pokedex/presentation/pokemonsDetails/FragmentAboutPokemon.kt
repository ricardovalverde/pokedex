package com.example.pokedex.presentation.pokemonsDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.pokedex.R
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.databinding.FragmentPokemonAboutBinding
import com.example.pokedex.presentation.pokemons.PokemonsViewModel

class FragmentAboutPokemon : Fragment() {
    private lateinit var binding: FragmentPokemonAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonAboutBinding.inflate(layoutInflater)


        val height = binding.textViewValueHeight
        val weight = binding.textViewValueWeight
        val ability1 = binding.textViewAbility1
        val ability2 = binding.textViewAbility2





        height.text = getString(R.string.height, HEIGHT.toString())
        weight.text = getString(R.string.weight, WEIGHT.toString())

        ability1.text = ABILITY_1
        ability2.text = ABILITY_2

        val viewModel: PokemonsViewModel =
            ViewModelProviders.of(this).get(PokemonsViewModel::class.java)

        viewModel.speciesLiveData.observe(viewLifecycleOwner, {
            it?.let {
                for (pos in it){
                    for (r in pos.specie){
                        for(s in r.languagesResponse.language){
                            if (s.toString() == "en"){
                                binding.textViewDescriptionPokemon.text = s.toString()
                                break
                            }
                        }
                    }
                }
            }
        })
        return binding.root
    }


    companion object {

        private var WEIGHT: Float = 0.0F
        private var HEIGHT: Float = 0.0F
        private lateinit var ABILITY_1: String
        private var ABILITY_2: String? = null

        fun data(pokemon: Pokemon) {
            WEIGHT = pokemon.weight
            HEIGHT = pokemon.height

            ABILITY_1 = pokemon.abilities[0].nameAbility.Ability.capitalize()

            if (pokemon.abilities.size > 1) {
                ABILITY_2 = pokemon.abilities[1].nameAbility.Ability.capitalize()
            }
        }
    }
}

