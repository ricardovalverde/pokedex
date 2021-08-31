package com.example.pokedex.presentation.pokemons

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.databinding.ItemPokemonsBinding
import com.example.pokedex.util.Colors
import com.example.pokedex.util.Images


class PokemonAdapter(
    private val list_pokemons: List<Pokemon>,
    private val context: Context,
    private val onItemClickListener: (pokemon: Pokemon) -> Unit

) : RecyclerView.Adapter<PokemonViewHolder>() {
    private lateinit var binding: ItemPokemonsBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        binding =
            ItemPokemonsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(list_pokemons[position], binding, onItemClickListener)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount() = list_pokemons.count()
}

class PokemonViewHolder(
    binding: ItemPokemonsBinding,
    private val context: Context,

    ) : RecyclerView.ViewHolder(binding.root) {

    private val name = binding.textViewNamePokemon
    private val image = binding.imageViewPokemonIcon
    private val id = binding.textViewIdPokemon
    private val type1 = binding.textViewType1Pokemon
    private val type2 = binding.textViewType2Pokemon
    private val cardViewBackGround = binding.cardViewPokemons


    fun bind(
        pokemon: Pokemon,
        binding: ItemPokemonsBinding,
        onItemClickListener: (pokemon: Pokemon) -> Unit
    ) {
        name.text = pokemon.name
        id.text = "#${pokemon.id}"
        type1.text = pokemon.type1.type
        type2.text = pokemon.type2?.type

        cardViewBackGround.setCardBackgroundColor(Colors.findColor(context, pokemon.type1.type))
        Colors.setDrawableBackgroundColor(context, pokemon.type1.type, type1)

        pokemon.type2?.type.let {
            type2.setBackgroundResource(R.drawable.type_background)
            Colors.setDrawableBackgroundColor(context, it, type2)
        }

        Images.loadImage(pokemon.image.image, image)

        binding.root.setOnClickListener {
            onItemClickListener.invoke(pokemon)
        }
    }
}