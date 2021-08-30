package com.example.pokedex.presentation.pokemons

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.databinding.ItemPokemonsBinding
import com.example.pokedex.util.Colors
import com.squareup.picasso.Picasso


class PokemonAdapter(private val list_pokemons: List<Pokemon>, private val context: Context) :
    RecyclerView.Adapter<PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding =
            ItemPokemonsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(list_pokemons[position])
    }

    override fun getItemCount() = list_pokemons.count()
}

class PokemonViewHolder(binding: ItemPokemonsBinding, private val context: Context) :
    RecyclerView.ViewHolder(binding.root) {
    private val name = binding.textViewNamePokemon
    private val image = binding.imageViewPokemonIcon
    private val id = binding.textViewIdPokemon
    private val type1 = binding.textViewType1Pokemon
    private val type2 = binding.textViewType2Pokemon
    private val cardViewBackGround = binding.cardViewPokemons


    fun bind(pokemon: Pokemon) {
        name.text = pokemon.name
        id.text = "#${pokemon.id}"
        type1.text = pokemon.type1.type
        type2.text = pokemon.type2?.type

        cardViewBackGround.setCardBackgroundColor(Colors.findColor(pokemon.type1.type, context))
        Colors.setBackgroundColor(pokemon.type1.type, context, type1)

        pokemon.type2?.type.let {
            type2.setBackgroundResource(R.drawable.type_background)
            Colors.setBackgroundColor(it, context, type2)
        }

        Picasso.get().load(pokemon.image.image).into(image)
    }
}