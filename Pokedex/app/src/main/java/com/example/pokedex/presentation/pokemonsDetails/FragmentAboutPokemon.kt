package com.example.pokedex.presentation.pokemonsDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pokedex.R
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.model.Specie
import com.example.pokedex.databinding.FragmentPokemonAboutBinding

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
        val description = binding.textViewDescriptionPokemon


        height.text = getString(R.string.height, HEIGHT.toString())
        weight.text = getString(R.string.weight, WEIGHT.toString())

        ability1.text = ABILITY_1
        ability2.text = ABILITY_2

        SPECIES.let {

            for (position in it) {
                for (item in position.FlavorsList) {
                    if (item.languagesResponse.language == "en") {
                        description.text = item.flavorText
                    }
                }
            }
        }

        return binding.root
    }

    companion object {

        private var WEIGHT: Float = 0.0F
        private var HEIGHT: Float = 0.0F
        private lateinit var ABILITY_1: String
        private var ABILITY_2: String? = null
        private lateinit var SPECIES: MutableList<Specie>

        fun data(
            pokemon: Pokemon,
            listSpecie: MutableList<Specie>

        ) {
            WEIGHT = pokemon.weight
            HEIGHT = pokemon.height
            SPECIES = listSpecie
            ABILITY_1 = pokemon.abilities[0].nameAbility.Ability.capitalize()

            if (pokemon.abilities.size > 1) {
                ABILITY_2 = pokemon.abilities[1].nameAbility.Ability.capitalize()
            }
        }
    }
}
