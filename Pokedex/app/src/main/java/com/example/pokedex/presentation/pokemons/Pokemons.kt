package com.example.pokedex.presentation.pokemons

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.databinding.ActivityPokemonsBinding
import com.example.pokedex.presentation.pokemonsDetails.FragmentAboutPokemon
import com.example.pokedex.presentation.pokemonsDetails.FragmentSearchPokemon
import com.example.pokedex.presentation.pokemonsDetails.FragmentStatsPokemon
import com.example.pokedex.presentation.pokemonsDetails.PokemonsDetails
import com.example.pokedex.util.Colors
import com.example.pokedex.util.Images

private lateinit var binding: ActivityPokemonsBinding

class Pokemons : AppCompatActivity() {
    private lateinit var adapterPokemon: PokemonAdapter
    private lateinit var listPokemons: List<Pokemon>
    private lateinit var searchFragment: FragmentSearchPokemon


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityPokemonsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val toolbar = binding.mainToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val viewModel: PokemonsViewModel =
            ViewModelProviders.of(this).get(PokemonsViewModel::class.java)

        viewModel.pokemonsLiveData.observe(this, {

            it?.let { pokemons ->
                listPokemons = pokemons
                pokemons.sortBy { pokemon -> pokemon.id }
                adapterPokemon = PokemonAdapter(pokemons, this@Pokemons) { pokemon ->

                    val intentDetails = PokemonsDetails.init(this@Pokemons, pokemon)

                    viewModel.getSpecie(pokemon.urlSpecie)
                    FragmentStatsPokemon.data(pokemon)
                    FragmentAboutPokemon.data(
                        pokemon,
                        viewModel.listSpecie,
                        viewModel.listCategory
                    )
                    startActivity(intentDetails)
                }
                with(binding.recyclerViewPokemons) {
                    layoutManager =
                        GridLayoutManager(this@Pokemons, 2, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapterPokemon.let { pokemonAdapter ->
                        adapter = pokemonAdapter
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        searchFragment = FragmentSearchPokemon()

        val searchItem: MenuItem? = menu?.findItem(R.id.action_search)
        val searchManager: SearchManager =
            baseContext.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        var searchView: SearchView? = null

        if (searchItem != null) {

            searchView = searchItem.actionView as SearchView
            with(searchView) { setSearchableInfo(searchManager.getSearchableInfo(componentName)) }

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(newText: String?): Boolean {
                    searchView.setQuery("", false)
                    searchView.onActionViewCollapsed()
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapterPokemon.filterObj.filter(newText?.lowercase())
                    return false
                }
            })
            searchItem.expandActionView()
        }
        return true
    }

    /*private fun filter(text: String) {
        val filterList: ArrayList<Pokemon> = arrayListOf()

        for (pokemon in listPokemons) {
            if (pokemon.name.lowercase().contains(text) ||
                pokemon.id.toString().contains(text) ||
                pokemon.type1.type.contains(text)
            ) {
                filterList.add(pokemon)
            }
        }
        adapterPokemon.filterList(filterList)
    }

    private fun removeFilter() {
        adapterPokemon.removeFilter()
    }*/


}