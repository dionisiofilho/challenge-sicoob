package com.dionisiofilho.sicoob.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.dionisiofilho.sicoob.R
import com.dionisiofilho.sicoob.application.helpers.ImageHelper
import com.dionisiofilho.sicoob.model.Movie

class MovieAdapter(val onClickMovie: (movie: Movie) -> Unit) : RecyclerView.Adapter<MovieAdapter.ItemMovideViewHolder>() {

    private val movies = arrayListOf<Movie>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovideViewHolder {
        return ItemMovideViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(viewHolder: ItemMovideViewHolder, position: Int) {

        val movie = movies[position]

        ImageHelper.clearImage(viewHolder.imageMovie)

        movie.posterPath?.let {
            ImageHelper.getImageFromMovie(it, viewHolder.imageMovie)
        }

        viewHolder.imageMovie.setOnClickListener { view ->
            onClickMovie(movie)
        }

    }

    fun addMovies(movies: List<Movie>, clear: Boolean = true) {

        if (clear) {
            this.movies.clear()
        }

        this.movies.addAll(movies)
        notifyDataSetChanged()

    }

    inner class ItemMovideViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageMovie: ImageView = view.findViewById(R.id.iv_movie_adapter)
    }
}