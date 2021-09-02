package com.example.pokedex.data.model

import com.example.pokedex.data.response.stats.BaseResponse

data class Stats(
    val base_stats: Pair<String, BaseResponse>
)
