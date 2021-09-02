package com.example.pokedex.presentation.pokemonsDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.response.stats.BaseResponse
import com.example.pokedex.databinding.FragmentPokemonStatsBinding

class FragmentPokemonStats : Fragment() {

    private lateinit var binding: FragmentPokemonStatsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonStatsBinding.inflate(layoutInflater)

        with(binding.recyclerViewStats) {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = StatsAdapter(STATS)
        }
        return binding.root
    }

    companion object {

        private var STATS: List<BaseResponse> = arrayListOf()

        fun data(pokemon: Pokemon) {
            STATS = pokemon.stats
        }
    }
}
