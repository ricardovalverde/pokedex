package com.example.pokedex.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.R
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.presentation.pokemons.Pokemons
import com.example.pokedex.util.Colors

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Colors.setStatusbarColor(this, this.window, R.color.type_ice, null)

        binding.containerCategoriePokemon.setOnClickListener {
            val intent = Intent(this@MainActivity, Pokemons::class.java)
            startActivity(intent)
        }
    }
}