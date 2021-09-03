package com.example.pokedex.presentation.pokemonsDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pokedex.R
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.databinding.FragmentPokemonAboutBinding
import java.util.*

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

        return binding.root
    }

    companion object {

        private var WEIGHT: Float = 0.0F
        private var HEIGHT: Float = 0.0F
        private lateinit var ABILITY_1: String
        private lateinit var ABILITY_2: String

        fun data(pokemon: Pokemon) {
            WEIGHT = pokemon.weight
            HEIGHT = pokemon.height

            ABILITY_1 = pokemon.abilities[0].nameAbility.Ability.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }
            ABILITY_2 = pokemon.abilities[1].nameAbility.Ability.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }
        }
    }
}
