package com.example.pokedex.presentation.pokemons

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pokedex.presentation.pokemonsDetails.FragmentAboutPokemon
import com.example.pokedex.presentation.pokemonsDetails.FragmentStatsPokemon

class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        Log.i("Fragment", position.toString())
        return when (position) {

            0 -> FragmentAboutPokemon()
            1 -> FragmentStatsPokemon()


            else -> FragmentAboutPokemon()

        }
    }
}