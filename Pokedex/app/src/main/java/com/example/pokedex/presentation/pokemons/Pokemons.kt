package com.example.pokedex.presentation.pokemons

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
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
        setViewModel()

        Colors.setStatusbarColor(this, this.window, R.color.pokemon_logo, null)
        Images.loadGif(this, R.drawable.pikachu, binding.pikachuGif)
        Colors.setStatusbarColor(this, this.window, R.color.background_Main, null)
        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        searchFragment = FragmentSearchPokemon()

        val searchItem: MenuItem? = menu?.findItem(R.id.action_search)
        val searchManager: SearchManager =
            baseContext.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        val searchView: SearchView?

        if (searchItem != null) {

            searchView = searchItem.actionView as SearchView

            with(searchView) {
                setSearchableInfo(searchManager.getSearchableInfo(componentName))

                searchView.setOnCloseListener(SearchView.OnCloseListener {
                    setViewModel()
                    false
                })

                searchView.maxWidth = Integer.MAX_VALUE
            }
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(newText: String?): Boolean {
                    if (newText?.isNotEmpty() == true) {
                        adapterPokemon.filterObj.filter(newText?.lowercase())
                        searchView.clearFocus()
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText?.isNotEmpty() == true) {
                        adapterPokemon.filterObj.filter(newText?.lowercase())
                    }
                    return true
                }
            })

            searchItem.expandActionView()
        }
        return true
    }

    private fun setViewModel() {

        val viewModel: PokemonsViewModel =
            ViewModelProviders.of(this).get(PokemonsViewModel::class.java)

        viewModel.pokemonsLiveData.observe(this, {

            it?.let { pokemons ->
                //listPokemons = pokemons
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
