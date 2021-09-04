package com.example.pokedex.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.example.pokedex.R

class Colors() {
    companion object Color {
        fun findColor(context: Context, type: String?): Int {
            return when (type) {
                "Normal" -> ContextCompat.getColor(context, R.color.type_normal)
                "Fire" -> ContextCompat.getColor(context, R.color.type_fire)
                "Fighting" -> ContextCompat.getColor(context, R.color.type_fighting)
                "Water" -> ContextCompat.getColor(context, R.color.type_water)
                "Flying" -> ContextCompat.getColor(context, R.color.type_flying)
                "Grass" -> ContextCompat.getColor(context, R.color.type_grass)
                "Poison" -> ContextCompat.getColor(context, R.color.type_poison)
                "Electric" -> ContextCompat.getColor(context, R.color.type_electric)
                "Ground" -> ContextCompat.getColor(context, R.color.type_ground)
                "Psychic" -> ContextCompat.getColor(context, R.color.type_psychic)
                "Rock" -> ContextCompat.getColor(context, R.color.type_rock)
                "Ice" -> ContextCompat.getColor(context, R.color.type_ice)
                "Bug" -> ContextCompat.getColor(context, R.color.type_bug)
                "Dragon" -> ContextCompat.getColor(context, R.color.type_dragon)
                "Ghost" -> ContextCompat.getColor(context, R.color.type_ghost)
                "Dark" -> ContextCompat.getColor(context, R.color.type_dark)
                "Steel" -> ContextCompat.getColor(context, R.color.type_steel)
                "Fairy" -> ContextCompat.getColor(context, R.color.type_fairy)

                else -> ContextCompat.getColor(context, R.color.white)
            }
        }

        fun setStatusbarColor(
            context: Context,
            window: Window,
            @ColorRes colorRes: Int?,
            @ColorInt colorInt: Int?
        ) {

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

            if (colorRes != null) {
                window.statusBarColor = ContextCompat.getColor(context, colorRes)
            }
            if (colorInt != null) {
                window.statusBarColor = colorInt
            }
        }

        fun setDrawableBackgroundColor(
            context: Context,
            type: String?,
            textView: TextView
        ): Drawable {
            val gradientDrawable = (textView.background as GradientDrawable).mutate()
            when (type) {

                "Normal" ->
                    (gradientDrawable as GradientDrawable).setColor(

                        ContextCompat.getColor(
                            context,
                            R.color.type_normal_dark
                        )
                    )
                "Grass" ->
                    (gradientDrawable as GradientDrawable).setColor(
                        ContextCompat.getColor(
                            context,
                            R.color.type_grass_dark
                        )
                    )
                "Fire" ->
                    (gradientDrawable as GradientDrawable).setColor(
                        ContextCompat.getColor(
                            context,
                            R.color.type_fire_dark
                        )
                    )
                "Fighting" ->
                    (gradientDrawable as GradientDrawable).setColor(
                        ContextCompat.getColor(
                            context,
                            R.color.type_fighting_dark
                        )
                    )
                "Water" ->
                    (gradientDrawable as GradientDrawable).setColor(
                        ContextCompat.getColor(
                            context,
                            R.color.type_water_dark
                        )
                    )
                "Flying" ->
                    (gradientDrawable as GradientDrawable).setColor(
                        ContextCompat.getColor(
                            context,
                            R.color.type_flying_dark
                        )
                    )
                "Poison" ->
                    (gradientDrawable as GradientDrawable).setColor(
                        ContextCompat.getColor(
                            context,
                            R.color.type_poison_dark
                        )
                    )
                "Electric" ->
                    (gradientDrawable as GradientDrawable).setColor(
                        ContextCompat.getColor(
                            context,
                            R.color.type_electric_dark
                        )
                    )
                "Ground" ->
                    (gradientDrawable as GradientDrawable).setColor(
                        ContextCompat.getColor(
                            context,
                            R.color.type_ground_dark
                        )
                    )
                "Psychic" ->
                    (gradientDrawable as GradientDrawable).setColor(
                        ContextCompat.getColor(
                            context,
                            R.color.type_psychic_dark
                        )
                    )
                "Rock" ->
                    (gradientDrawable as GradientDrawable).setColor(
                        ContextCompat.getColor(
                            context,
                            R.color.type_rock_dark
                        )
                    )
                "Ice" ->
                    (gradientDrawable as GradientDrawable).setColor(
                        ContextCompat.getColor(
                            context,
                            R.color.type_ice_dark
                        )
                    )
                "Bug" ->
                    (gradientDrawable as GradientDrawable).setColor(
                        ContextCompat.getColor(
                            context,
                            R.color.type_bug_dark
                        )
                    )
                "Dragon" ->
                    (gradientDrawable as GradientDrawable).setColor(
                        ContextCompat.getColor(
                            context,
                            R.color.type_dragon_dark
                        )
                    )
                "Ghost" ->
                    (gradientDrawable as GradientDrawable).setColor(
                        ContextCompat.getColor(
                            context,
                            R.color.type_ghost_dark
                        )
                    )
                "Dark" ->
                    (gradientDrawable as GradientDrawable).setColor(
                        ContextCompat.getColor(
                            context,
                            R.color.type_dark_dark
                        )
                    )
                "Steel" ->
                    (gradientDrawable as GradientDrawable).setColor(
                        ContextCompat.getColor(
                            context,
                            R.color.type_steel_dark
                        )
                    )
                "Fairy" ->
                    (gradientDrawable as GradientDrawable).setColor(
                        ContextCompat.getColor(
                            context,
                            R.color.type_fairy_dark
                        )
                    )

                else -> (gradientDrawable as GradientDrawable).setColor(
                    ContextCompat.getColor(
                        context,
                        android.R.color.transparent
                    )
                )
            }
            return gradientDrawable
        }
    }
}

