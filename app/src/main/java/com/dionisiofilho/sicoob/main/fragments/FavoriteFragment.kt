package com.dionisiofilho.sicoob.main.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dionisiofilho.sicoob.R
import com.dionisiofilho.sicoob.adapters.MovieAdapter
import com.dionisiofilho.sicoob.application.bases.BaseFragment
import com.dionisiofilho.sicoob.interfaces.IMovie
import com.dionisiofilho.sicoob.model.Movie
import com.dionisiofilho.sicoob.moviedetail.MovieDetailActivity
import com.dionisiofilho.sicoob.presenters.MoviePresenter


class FavoriteFragment : BaseFragment(), IMovie {

    private lateinit var moviesFavorites: RecyclerView

    private val favoriteAdapter: MovieAdapter by lazy {
        MovieAdapter { onClickMovie(it) }
    }

    private val moviePresenter: MoviePresenter by lazy {
        MoviePresenter(requireContext(), this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        configureRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        loadMoviesFavorite()
    }

    private fun loadMoviesFavorite() {
        moviePresenter.getFavoriteMovies { movies -> favoriteAdapter.addMovies(movies) }
    }

    private fun configureRecyclerView() {
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)
        moviesFavorites.layoutManager = gridLayoutManager
        moviesFavorites.adapter = favoriteAdapter
    }

    private fun initViews(view: View) {
        moviesFavorites = view.findViewById(R.id.rv_movies_favorites)

    }

    private fun onClickMovie(movie: Movie) {
        val intentDetail = Intent(requireContext(), MovieDetailActivity::class.java)
        intentDetail.putExtra(MovieDetailActivity.IDMovie, movie.id)
        startActivityWithAnimation(intentDetail)
    }

    override fun onSuccesGetMovie(movies: List<Movie>) {
    }

    override fun onSuccessGetDetailsMovie(movie: Movie) {
    }


}
