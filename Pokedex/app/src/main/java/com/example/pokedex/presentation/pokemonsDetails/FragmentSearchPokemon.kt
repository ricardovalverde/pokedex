package com.example.pokedex.presentation.pokemonsDetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.databinding.FragmentSearchPokemonBinding
import com.example.pokedex.presentation.pokemons.Pokemons
import com.example.pokedex.util.Colors
import com.squareup.picasso.Picasso

class FragmentSearchPokemon : Fragment() {
    private lateinit var binding: FragmentSearchPokemonBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchPokemonBinding.inflate(layoutInflater)

        val name = binding.textViewSearchNamePokemon
        val id = binding.textViewIdSearchPokemon
        val type1 = binding.textViewType1SearchPokemon
        val type2 = binding.textViewType2SearchPokemon
        val image = binding.imageViewPokemonIconSearch
        val cardView = binding.cardViewSearchPokemons
        val arrowback = binding.arrowBackSearch
        arrowback.setOnClickListener {
            Pokemons.removeFragment(FragmentManager,this)
        }

        name.text = NAME
        id.text = "#${ID.toString()}"
        type1.text = TYPE1
        TYPE2.let {
            type2.text = it
            Colors.setDrawableBackgroundColor(CONTEXT, TYPE2,type2)

        }
        Picasso.get().load(IMAGE).into(image)
        Colors.setDrawableBackgroundColor(CONTEXT, TYPE1,type1)
        cardView.setCardBackgroundColor(Colors.findColor(CONTEXT, TYPE1))






        return binding.root
    }

    companion object {
        private lateinit var CONTEXT: Context
        private lateinit var FragmentManager: FragmentManager
        private lateinit var NAME: String
        private var ID: Int = 0
        private lateinit var TYPE1: String
        private var TYPE2: String? = null
        private lateinit var IMAGE: String


        fun init(context: Context, pokemon: Pokemon, supportFragmentManager: FragmentManager) {
            CONTEXT = context
            FragmentManager = supportFragmentManager
            NAME = pokemon.name
            ID = pokemon.id
            TYPE1 = pokemon.type1.type
            TYPE2 = pokemon.type2?.type
            IMAGE = pokemon.image.image
        }
    }

}
