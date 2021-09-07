package com.example.pokedex.presentation.pokemonsDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.databinding.ActivityPokemonsDetailsBinding
import com.example.pokedex.util.Colors
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso

class PokemonsDetails : AppCompatActivity() {
    private lateinit var binding: ActivityPokemonsDetailsBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonsDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setDetailsData()
        setTabLayout()
        finishActivity()

    }

    companion object {
        private const val EXTRA_NAME = "EXTRA_NAME"
        private const val EXTRA_IMAGE = "EXTRA_IMAGE"
        private const val EXTRA_ID = "EXTRA_ID"
        private const val EXTRA_TYPE1 = "EXTRA_TYPE1"
        private val EXTRA_TYPE2: String? = null

        fun init(context: Context, pokemon: Pokemon): Intent {
            return Intent(context, PokemonsDetails::class.java).apply {
                putExtra(EXTRA_NAME, pokemon.name)
                putExtra(EXTRA_IMAGE, pokemon.image.image)
                putExtra(EXTRA_ID, "#${pokemon.id}")
                putExtra(EXTRA_TYPE1, pokemon.type1.type)
                putExtra(EXTRA_TYPE2, pokemon.type2?.type)
            }
        }
    }

    private fun setDetailsData() {
        val namePokemon: String? = intent.getStringExtra(EXTRA_NAME)
        val idPokemon: String? = intent.getStringExtra(EXTRA_ID)
        val imagePokemon: String? = intent.getStringExtra(EXTRA_IMAGE)
        val type1: String? = intent.getStringExtra(EXTRA_TYPE1)
        val type2: String? = intent.getStringExtra(EXTRA_TYPE2)

        val linearLayout: LinearLayout = binding.containerLinearLayoutPokemonDetails
        val textViewName: TextView = binding.namePokemonDetails
        val textViewId: TextView = binding.idPokemonDetails
        val imageViewPokemon: ImageView = binding.imagePokemonDetails
        val textViewType1: TextView = binding.textViewType1PokemonDetails
        val textViewType2: TextView = binding.textViewType2PokemonDetails

        textViewName.text = namePokemon
        textViewType1.text = type1
        textViewId.text = idPokemon

        linearLayout.setBackgroundColor(Colors.findColor(this, type1.toString()))
        Colors.setDrawableBackgroundColor(this, type1, textViewType1)

        type2.let {
            textViewType2.text = it
            Colors.setDrawableBackgroundColor(this, type2, textViewType2)
        }

        Picasso.get().load(imagePokemon).into(imageViewPokemon)
        Colors.setStatusbarColor(this, this.window, null, Colors.findColor(this, type1))
    }

    private fun setTabLayout() {
        tabLayout = binding.tabsDetails
        viewPager = binding.viewPagerDetails

        val adapter = FragmentAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, pos ->
            when (pos) {
                0 -> tab.text = "About"
                1 -> tab.text = "Base Stats"
            }
        }.attach()
    }

    private fun finishActivity() {
        binding.arrowBackDetails.setOnClickListener {
            finish()
        }
    }
}


