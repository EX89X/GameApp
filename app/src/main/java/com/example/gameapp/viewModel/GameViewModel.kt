package com.example.gameapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.gameapp.data.gamesItem
import com.example.gameapp.model.Repsitory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(application: Application): AndroidViewModel(application){

    var repository: Repsitory = Repsitory()

    fun getGames():LiveData<List<gamesItem>> {
        return repository.getGame()
    }

    fun addGames(game: gamesItem) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addGame(game)
        }
    }

    fun deleteGames(game:gamesItem) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteGame(game)
        }
    }
}