package com.example.gameapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gameapp.R
import com.example.gameapp.data.gamesItem
import kotlinx.android.synthetic.main.show_itemrow.view.*

class ShowRVA(private var games:ArrayList<gamesItem>,var click: ClickListener): RecyclerView.Adapter<ShowRVA.ItemViewHolder>() {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.show_itemrow,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val game = games[position]
        lateinit var saveButton1: Button
        lateinit var detailsbutton1: Button
        var Countnumber=1




        holder.itemView.apply {
            saveButton1 = findViewById(R.id.button1)
            detailsbutton1 = findViewById(R.id.button2)
            textView1.text = game.title

            saveButton1.setOnClickListener {
                click.saveData(game)
            }
            detailsbutton1.setOnClickListener {
                click.details(game,Countnumber)
            }

            if (game.thumbnail != null) {
                Glide.with(CL)
                    .load(game.thumbnail)
                    .into(imageView1)
            }
        }

    }

        fun update(Updated: ArrayList<gamesItem>) {
            println("UPDATING DATA")
            this.games = Updated
            notifyDataSetChanged()

        }

        interface ClickListener {
            fun saveData(games:gamesItem)
            fun details(games:gamesItem,count:Int)

        }


        override fun getItemCount() = games.size
    }
