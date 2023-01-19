package com.example.gameapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gameapp.R
import com.example.gameapp.data.gamesItem
import kotlinx.android.synthetic.main.show_itemrow.view.*

class SavedRVA(private var games:ArrayList<gamesItem>,var click: ClickListener):RecyclerView.Adapter<SavedRVA.ItemViewHolder>() {
    class ItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_saved_game,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val game = games[position]
        lateinit var deletebuttn: Button


        holder.itemView.apply {
            deletebuttn = findViewById(R.id.deletebutton)
            textView1.text = game.title

            deletebuttn.setOnClickListener {
                click.deleteData(game)
            }


            if (game.thumbnail != null) {
                Glide.with(CL)
                    .load(game.thumbnail)
                    .into(imageView1)
            }
        }
    }

    override fun getItemCount() = games.size

    fun update(Updated: ArrayList<gamesItem>) {
        println("UPDATING DATA")
        this.games = Updated
        notifyDataSetChanged()

    }

    interface ClickListener {
        fun deleteData(games: gamesItem)
    }
}