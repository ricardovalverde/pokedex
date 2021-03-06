package com.example.pokedex.presentation.pokemons

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
private lateinit var viewModel: PokemonsViewModel

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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        viewModel = ViewModelProviders.of(this).get(PokemonsViewModel::class.java)

        setViewModel(viewModel)

        Images.loadGif(this, R.drawable.pikachu, binding.pikachuGif)
        //Colors.setStatusbarColor(this, this.window, R.color.background_Main, null)
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
                    adapterPokemon.filterObj.filter("")
                    false
                })
                searchView.maxWidth = Integer.MAX_VALUE
            }
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(newText: String?): Boolean {
                    if (newText?.isNotEmpty() == true) {
                        adapterPokemon.filterObj.filter(newText.lowercase())
                        searchView.clearFocus()
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    if (newText?.isNotEmpty() == true) {
                        adapterPokemon.filterObj.filter(newText.lowercase())
                    }
                    return true
                }
            })
            searchItem.expandActionView()
            binding.recyclerViewPokemons.smoothScrollBy(0, 0)
        }
        return true
    }

    private fun setViewModel(viewModel: PokemonsViewModel) {

        viewModel.pokemonsLiveData.observe(this) {

            it?.let { pokemons ->
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
        }
        Colors.setStatusbarColor(this, this.window, R.color.pokemon_logo, null)
        viewModel.viewFlipper.observe(this, Observer {
            Handler(Looper.getMainLooper()).postDelayed({
                it?.let { viewFlipper ->
                    binding.mainViewFlipper.displayedChild = viewFlipper
                }
            }, 5000)
        })
        viewModel.getPokemons()
    }
}
