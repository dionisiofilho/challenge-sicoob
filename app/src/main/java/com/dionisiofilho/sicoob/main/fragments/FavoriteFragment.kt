package com.dionisiofilho.sicoob.main.fragments


import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dionisiofilho.sicoob.R
import com.dionisiofilho.sicoob.adapters.MovieAdapter
import com.dionisiofilho.sicoob.application.bases.BaseFragment
import com.dionisiofilho.sicoob.application.helpers.NetworkHelper
import com.dionisiofilho.sicoob.application.helpers.ToastHelper
import com.dionisiofilho.sicoob.extensions.gone
import com.dionisiofilho.sicoob.extensions.visible
import com.dionisiofilho.sicoob.interfaces.IMovie
import com.dionisiofilho.sicoob.model.Movie
import com.dionisiofilho.sicoob.moviedetail.MovieDetailActivity
import com.dionisiofilho.sicoob.presenters.MoviePresenter


class FavoriteFragment : BaseFragment(), IMovie {

    private lateinit var moviesFavorites: RecyclerView
    private lateinit var containerIsEmpty: ConstraintLayout

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


        moviePresenter.getFavoriteMovies { movies ->
            run {
                favoriteAdapter.addMovies(movies)
                if (movies.isEmpty()) {
                    containerIsEmpty.visible()
                } else {
                    containerIsEmpty.gone()
                }
            }
        }
    }

    private fun configureRecyclerView() {
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)
        moviesFavorites.layoutManager = gridLayoutManager
        moviesFavorites.adapter = favoriteAdapter
    }

    private fun initViews(view: View) {
        moviesFavorites = view.findViewById(R.id.rv_movies_favorites)
        containerIsEmpty = view.findViewById(R.id.cl_container_movie_is_empty)

    }

    private fun onClickMovie(movie: Movie) {
        if (NetworkHelper.isOnline()) {
            val intentDetail = Intent(requireContext(), MovieDetailActivity::class.java)
            intentDetail.putExtra(MovieDetailActivity.IDMovie, movie.id)
            startActivityWithAnimation(intentDetail)
        } else {
            ToastHelper.showToastLong(R.string.offline)
        }
    }

    override fun onSuccesGetMovie(movies: List<Movie>, page: Int) {
    }

    override fun onSuccessGetDetailsMovie(movie: Movie) {
    }

    override fun onSuccessSearchMovie(movies: List<Movie>) {
    }


}
