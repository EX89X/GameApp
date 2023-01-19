package com.example.gameapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.gameapp.databinding.ActivityGameDetailsBinding


class GameDetails : AppCompatActivity() {

    lateinit var binding: ActivityGameDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGameDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            // on below line we are setting our message to our text view.
            textView1.text = intent.extras?.getString("title")
            textView2.text = intent.extras?.getString("description")
            val thumbnail= intent.extras?.getString("thumbnail")
            Glide.with(cl2)
                .load(thumbnail)
                .into(imageView1)
        }
    }
}