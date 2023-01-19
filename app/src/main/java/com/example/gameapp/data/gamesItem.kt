package com.example.gameapp.data

data class gamesItem(
    val id: Int,
    val title:String,
    val thumbnail:String,
    val game_url: String,
    val platform: String,
    val genre: String,
    val short_description: String,
    val clickCount:Int
)
