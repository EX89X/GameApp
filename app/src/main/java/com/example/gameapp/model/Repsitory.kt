package com.example.gameapp.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gameapp.data.gamesItem
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Repsitory {

    val db = Firebase.firestore
    private var gamearray: ArrayList<gamesItem> = arrayListOf()
    private val gamesLiveData = MutableLiveData<List<gamesItem>>()


    fun getGame(): LiveData<List<gamesItem>> {
        gamearray.clear()
        db.collection("GameApp")
            .get()
            .addOnSuccessListener {result ->
                for (document in result) {
                    gamearray.add(gamesItem(document.id.toInt(),
                        document.data.get("title").toString()
                        ,document.data.get("thumbnail").toString(),
                        document.data.get("game_url").toString(),
                        document.data.get("genre").toString(),
                        document.data.get("platform").toString(),
                        document.data.get("short_description").toString(),
                        0
                    ))
                }
                gamesLiveData.postValue(gamearray)
            }
            .addOnFailureListener { exception ->
                Log.w("MainActivity", "Error getting documents.", exception)
            }
        return gamesLiveData
    }


    fun deleteGame(game:gamesItem) {
        db.collection("GameApp").document(game.id.toString()).delete()
    }

    fun addGame(game:gamesItem){
        db.collection("Games").add(game)
    }

}