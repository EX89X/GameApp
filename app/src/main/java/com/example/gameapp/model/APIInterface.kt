package com.example.gameapp.model

import com.example.gameapp.data.game

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface APIInterface  {
    @GET("games")
    fun getGames(@Query("platform")platform:String):Call<game>

    @GET("games")
    fun getGamesCategory(@Query("category")category:String):Call<game>
}