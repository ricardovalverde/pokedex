package com.example.pokedex.presentation.pokemonsDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pokedex.databinding.FragmentSearchPokemonBinding

class FragmentSearchPokemon : Fragment() {
    private lateinit var binding: FragmentSearchPokemonBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchPokemonBinding.inflate(layoutInflater)
        binding.textViewSearchNamePokemon.text = "Foi"


        return binding.root
    }


}
