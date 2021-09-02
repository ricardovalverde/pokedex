package com.example.pokedex.presentation.pokemonsDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.data.response.stats.BaseResponse
import com.example.pokedex.databinding.ItemStatsBinding

class StatsAdapter(
    private val listStats: List<BaseResponse>
) : RecyclerView.Adapter<StatsViewHolder>(
) {
    private lateinit var binding: ItemStatsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        binding = ItemStatsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        holder.bind(listStats[position])
    }

    override fun getItemCount() = listStats.count()
}

class StatsViewHolder(binding: ItemStatsBinding) : RecyclerView.ViewHolder(binding.root) {
    private val nameStats = binding.textViewNameStats
    private val valueStats = binding.textViewValueStats
    private val linearIndicator = binding.linearProgressDetails

    fun bind(listStats: BaseResponse) {
        when (listStats.base_stat_name.nameBaseStat) {
            "special-attack" -> nameStats.text = itemView.context.getString(R.string.sp_atk)
            "special-defense" -> nameStats.text = itemView.context.getString(R.string.sp_def)
            else -> nameStats.text = listStats.base_stat_name.nameBaseStat.capitalize()
        }
        valueStats.text = listStats.base_stat.toString()
        linearIndicator.progress = listStats.base_stat
    }
}