package com.dionisiofilho.sicoob.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.dionisiofilho.sicoob.R
import com.dionisiofilho.sicoob.application.helpers.ImageHelper
import com.dionisiofilho.sicoob.extensions.getAppContext
import com.dionisiofilho.sicoob.model.Movie

class MovieAdapter(val onClickMovie: (movie: Movie) -> Unit) : RecyclerView.Adapter<MovieAdapter.ItemMovideViewHolder>() {

    private val movies = arrayListOf<Movie>()
    private lateinit var animation: Animation
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovideViewHolder {
        configureAnimation()
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
            viewHolder.imageMovie.startAnimation(animation)
        }

        viewHolder.imageMovie.setOnClickListener { view ->
            onClickMovie(movie)
        }

    }

    private fun clearLayoutImage(imageMovie: ImageView) {
        imageMovie.setImageDrawable(null)
    }

    fun addMovies(movies: ArrayList<Movie>, notify: Boolean = true) {
        this.movies.clear()
        this.movies.addAll(movies)

        if (notify) {
            notifyDataSetChanged()
        }
    }

    private fun configureAnimation() {
        animation = AnimationUtils.loadAnimation(getAppContext(), R.anim.zoom)
    }

    fun clear() {
        this.movies.clear()
        notifyDataSetChanged()
    }


    inner class ItemMovideViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val imageMovie: ImageView = view.findViewById(R.id.iv_movie_adapter)
    }
}