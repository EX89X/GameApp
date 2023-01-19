package com.example.gameapp.view


import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gameapp.data.game
import com.example.gameapp.data.gamesItem
import com.example.gameapp.databinding.ActivityHomePageBinding
import com.example.gameapp.model.APIClient
import com.example.gameapp.model.APIInterface
import com.example.gameapp.view.GameDetails
import com.example.gameapp.view.SavedGame
import com.example.gameapp.view.ShowRVA
import com.example.gameapp.viewModel.GameViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePage : AppCompatActivity(), ShowRVA.ClickListener {

    lateinit var mainViewModel: GameViewModel
    lateinit var gamesArray: ArrayList<gamesItem>
    lateinit var mostViewArray: ArrayList<gamesItem>
    var category = arrayListOf("zombie","shooter","fighting","sports")
    lateinit var binding: ActivityHomePageBinding
    var count=0
    var spannerValue=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mainViewModel = ViewModelProvider(this).get(GameViewModel::class.java)


        gamesArray = ArrayList()
        mostViewArray = ArrayList()

        val adapter = ShowRVA(gamesArray, this@HomePage)
        binding.pcgamesRC.adapter = adapter
        binding.pcgamesRC.layoutManager = GridLayoutManager(this@HomePage, 2)




        binding.apply {
           // APIRequest(count)
          //  adapter.update(gamesArray)
            bycategory()
            adapter.update(gamesArray)
        }
    }


    fun APIRequest(count: Int) {
        val apiClient = APIClient.getClient()
        if (apiClient != null) {
            val apiInterface = apiClient.create(APIInterface::class.java)
            apiInterface.getGames("pc").enqueue(object : Callback<game> {
                override fun onResponse(call: Call<game>, response: Response<game>) {
                    val body = response.body()
                    if (body != null) {
                        val games = body
                        gamesArray.clear()
                        for (Games in games) {
                            gamesArray.add(
                                gamesItem(
                                    Games.id,
                                    Games.title,
                                    Games.thumbnail,
                                    Games.game_url,
                                    Games.platform,
                                    Games.short_description,
                                    Games.short_description,
                                    count
                                )
                            )
                        }
                        binding.apply {
                            val adapter = ShowRVA(gamesArray, this@HomePage)
                            binding.pcgamesRC.adapter = adapter
                            binding.pcgamesRC.layoutManager = GridLayoutManager(this@HomePage, 2)
                        }
                    }
                }

                override fun onFailure(call: Call<game>, t: Throwable) {
                    Log.d("error", "THIS IS THE ERROR $t.message")
                }
            })
        }
    }


    fun APIRequest(sortby:String) {
        val apiClient = APIClient.getClient()
        if (apiClient != null) {
            val apiInterface = apiClient.create(APIInterface::class.java)
            apiInterface.getGamesCategory(sortby).enqueue(object : Callback<game> {
                override fun onResponse(call:Call<game>, response: Response<game>) {
                    val body = response.body()
                    if (body != null) {
                        Log.d("here","here")
                        val games = body
                        gamesArray.clear()
                        for(Games in games) {
                            gamesArray.add(gamesItem(Games.id,Games.title,Games.thumbnail,Games.game_url,Games.platform,Games.short_description,Games.short_description,count))
                        }
                        binding.apply {
                            val adapter = ShowRVA(gamesArray, this@HomePage)
                            Log.d("gamesArray",gamesArray.toString())
                            binding.pcgamesRC.adapter = adapter
                            binding.pcgamesRC.layoutManager = GridLayoutManager(this@HomePage,2)
                        }
                    }
                }
                override fun onFailure(call: Call<game>, t: Throwable) {
                    Log.d("error", "THIS IS THE ERROR $t.message")
                }
            })
        }
    }


    override fun saveData(games:gamesItem) {
       // val intent = Intent(this,SavedGame::class.java)
        intent.putExtra("title", games.title)
        intent.putExtra("description", games.short_description)
        intent.putExtra("thumbnail", games.thumbnail)
        startActivity(intent)
        mainViewModel.addGames(games)
        Toast.makeText(this@HomePage,"Added to database", Toast.LENGTH_LONG).show()


    }

    override fun details(games:gamesItem,count:Int) {
        //here will go to Game details
        val intent = Intent(this,GameDetails::class.java)
        intent.putExtra("title", games.title)
        intent.putExtra("description", games.short_description)
        intent.putExtra("thumbnail", games.thumbnail)
        startActivity(intent)

       counNumberOfClick(count)


    }

    fun bycategory() {
        binding.apply {
            val spinnerAdapter = ArrayAdapter(this@HomePage,R.layout.simple_spinner_item,category)
            spinner1.adapter = spinnerAdapter
            spinner1.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener { override fun onItemSelected(parent: AdapterView<*>,view:View,position:Int,id: Long) {
                    spannerValue= parent.getItemAtPosition(position).toString()
                    print(spannerValue)
                    //Log.d("tset",spannerValue)
                APIRequest(spannerValue)
                Log.d("tset2",spannerValue)
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
    }

    fun counNumberOfClick(count:Int){
        var count=+count
        APIRequest(count)

    }
}