package com.example.pokedex.data.response.sprite

import com.example.pokedex.data.model.Sprite
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FrontImageResponse(
    @Json(name = "front_default")
    val image: String
) {
    fun getImageDream() = Sprite(
        image = this.image
    )
}