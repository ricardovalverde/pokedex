package com.example.pokedex.presentation.pokemons

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.databinding.ActivityPokemonsBinding
import com.example.pokedex.presentation.pokemonsDetails.FragmentAboutPokemon
import com.example.pokedex.presentation.pokemonsDetails.FragmentStatsPokemon
import com.example.pokedex.util.Colors
import com.example.pokedex.util.Images

class Pokemons : AppCompatActivity() {
    private lateinit var binding: ActivityPokemonsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPokemonsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewModel: PokemonsViewModel =
            ViewModelProviders.of(this).get(PokemonsViewModel::class.java)

        viewModel.pokemonsLiveData.observe(this, {
            it?.let { pokemons ->
                with(binding.recyclerViewPokemons) {
                    layoutManager =
                        GridLayoutManager(this@Pokemons, 2, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = PokemonAdapter(pokemons, this@Pokemons) { pokemon ->

                        val intentDetails = PokemonsDetails.init(this@Pokemons, pokemon)
                        viewModel.getSpecie(pokemon.urlSpecie)
                        FragmentStatsPokemon.data(pokemon)
                        FragmentAboutPokemon.data(pokemon, viewModel.listSpecie)
                        startActivity(intentDetails)
                    }
                }
            }
        })

        Colors.setStatusbarColor(this, this.window, R.color.pokemon_logo, null)
        Images.loadGif(this, R.drawable.pikachu, binding.pikachuGif)

        viewModel.viewFlipper.observe(this, Observer {

            it?.let { viewFlipper ->
                binding.mainViewFlipper.displayedChild = viewFlipper
            }
        })

        viewModel.getPokemons()
        /*Handler(Looper.getMainLooper()).postDelayed({
            viewModel.getPokemons()
        }, 5000)*/
    }
}