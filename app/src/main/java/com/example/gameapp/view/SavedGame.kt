package com.example.gameapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gameapp.data.gamesItem
import com.example.gameapp.databinding.ActivitySavedGameBinding
import com.example.gameapp.viewModel.GameViewModel

class SavedGame : AppCompatActivity(), SavedRVA.ClickListener {


    lateinit var mainViewModel: GameViewModel
    lateinit var SavedRVA:SavedRVA
    lateinit var gamesSaved:ArrayList<gamesItem>
    lateinit var binding:ActivitySavedGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySavedGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel= ViewModelProvider(this).get(GameViewModel::class.java)
        SavedRVA=SavedRVA(gamesSaved,this)
        binding.rec.adapter=SavedRVA
        binding.rec.layoutManager= LinearLayoutManager(this)


    }

    override fun deleteData(games:gamesItem) {
        TODO("Not yet implemented")
    }
}