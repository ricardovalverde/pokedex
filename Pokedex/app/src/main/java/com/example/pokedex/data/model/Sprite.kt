package com.example.pokedex.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sprite(
    val image: String
) : Parcelable
