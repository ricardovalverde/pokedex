package com.example.pokedex.util

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

class Images {
    companion object {
        fun loadGif(context: Context, @DrawableRes drawable: Int, image: ImageView) {
            Glide.with(context).asGif().load(drawable).into(image)
        }
    }
}