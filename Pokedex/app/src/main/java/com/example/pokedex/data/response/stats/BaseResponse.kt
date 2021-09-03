package com.example.pokedex.data.response.stats

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponse(

    @Json(name = "base_stat")
    val base_stat: Int,

    @Json(name = "stat")
    val base_stat_name: NameBaseStat

)

