package com.dionisiofilho.sicoob.adapters

import SlideFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.dionisiofilho.sicoob.model.Movie

class SlideAdapter(fragmentManager: FragmentManager?) : FragmentPagerAdapter(fragmentManager) {

    private val movies = arrayListOf<Movie>()

    override fun getItem(position: Int): Fragment {
        return SlideFragment.newInstance(movies[position])
    }

    override fun getCount(): Int {
        return movies.size
    }

    fun addMovies(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

}