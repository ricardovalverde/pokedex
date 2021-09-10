package com.example.pokedex

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.databinding.ActivitySplashScreenBinding
import com.example.pokedex.presentation.MainActivity
import com.example.pokedex.util.Colors
import com.example.pokedex.util.Images

private lateinit var binding: ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Images.loadGif(this, R.drawable.sss, binding.imageSplash)
        Colors.setStatusbarColor(this, this.window, R.color.black, null)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, 6000)
    }
}
