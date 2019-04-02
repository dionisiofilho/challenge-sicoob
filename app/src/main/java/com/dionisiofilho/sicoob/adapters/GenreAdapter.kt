package com.dionisiofilho.sicoob.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dionisiofilho.sicoob.R
import com.dionisiofilho.sicoob.model.Genre

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.ItemGenreViewHolder>() {


    private val genres: ArrayList<Genre> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemGenreViewHolder {
        return ItemGenreViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false))
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    override fun onBindViewHolder(holder: GenreAdapter.ItemGenreViewHolder, position: Int) {

        val genre = genres[position]

        holder.genre.text = genre.name
    }

    fun addAll(genres: List<Genre>) {
        this.genres.clear()
        this.genres.addAll(genres)
        notifyDataSetChanged()
    }


    inner class ItemGenreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val genre: TextView = view.findViewById(R.id.tv_genre_name)
    }


}