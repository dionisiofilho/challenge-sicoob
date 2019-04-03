package com.dionisiofilho.sicoob.main.fragments


import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.dionisiofilho.sicoob.R
import com.dionisiofilho.sicoob.adapters.MovieAdapter
import com.dionisiofilho.sicoob.application.bases.BaseFragment
import com.dionisiofilho.sicoob.application.helpers.NetworkHelper
import com.dionisiofilho.sicoob.extensions.dimiss
import com.dionisiofilho.sicoob.extensions.gone
import com.dionisiofilho.sicoob.extensions.visible
import com.dionisiofilho.sicoob.interfaces.IMovie
import com.dionisiofilho.sicoob.model.Movie
import com.dionisiofilho.sicoob.moviedetail.MovieDetailActivity
import com.dionisiofilho.sicoob.presenters.MoviePresenter

class HomeFragment : BaseFragment(), IMovie, SearchView.OnQueryTextListener {


    private var loading: Boolean = true
    private lateinit var searchView: SearchView
    private lateinit var recyclerViewMovie: RecyclerView
    private lateinit var containerMovieIsEmpty: ConstraintLayout
    private lateinit var containerOffline: ConstraintLayout
    private lateinit var swipeHome: SwipeRefreshLayout

    private var page: Int = 1


    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter { onClickMovieAdapter(it) }
    }

    private val moviePresenter: MoviePresenter by lazy {
        MoviePresenter(requireContext(), this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        configureListMovie()
        configureSearchView()
        configureSwipe()
        getMovies()
    }

    private fun configureSwipe() {
        swipeHome.setOnRefreshListener {
            containerOffline.gone()
            page = 1
            getMovies()
        }
    }

    private fun configureSearchView() {
        searchView.setOnQueryTextListener(this)

        searchView.findViewById<ImageView>(R.id.search_close_btn)?.apply {
            setOnClickListener {
                searchView.setQuery("", false)
                page = 1
                getMovies()
            }
        }
    }


    private fun initViews() {
        view?.let {
            recyclerViewMovie = it.findViewById(R.id.rv_main_movies)
            searchView = it.findViewById(R.id.sv_movie)
            containerMovieIsEmpty = it.findViewById(R.id.cl_container_movie_is_empty_home)
            containerOffline = it.findViewById(R.id.cl_container_offline_home)
            swipeHome = it.findViewById(R.id.sw_home)
        }
    }

    private fun configureListMovie() {
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)
        recyclerViewMovie.layoutManager = gridLayoutManager
        recyclerViewMovie.adapter = movieAdapter

        recyclerViewMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {

                recyclerView?.let { rv ->

                    val visibleItemCount = gridLayoutManager.childCount
                    val totalItemCount = gridLayoutManager.itemCount
                    val pastPossibleItem = gridLayoutManager.findFirstVisibleItemPosition() + 10

                    if ((visibleItemCount + pastPossibleItem) >= (totalItemCount)) {
                        if (loading) {
                            loading = false
                            getMovies()
                        }
                    }
                }
            }
        })
    }

    private fun getMovies() {

        if (searchView.query.isEmpty()) {
            moviePresenter.getMovies(page)
        } else {
            moviePresenter.searchMovie(search = searchView.query.toString(), page = page)
        }

    }

    private fun onClickMovieAdapter(movie: Movie) {
        val intentDetail = Intent(requireContext(), MovieDetailActivity::class.java)
        intentDetail.putExtra(MovieDetailActivity.IDMovie, movie.id)
        startActivityWithAnimation(intentDetail)
    }


    override fun onSuccessSearchMovie(movies: List<Movie>) {
        movieAdapter.addMovies(movies)
    }

    override fun onSuccesGetMovie(movies: List<Movie>, page: Int) {

        swipeHome.dimiss()

        if (movies.isEmpty() && page == 1) {
            containerMovieIsEmpty.visible()
        } else {
            containerMovieIsEmpty.gone()
        }

        movieAdapter.addMovies(movies, page == 1)

        loading = !(movies.isEmpty() && this.page != 0)
        this.page = page + 1


    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        page = 1
        getMovies()
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun showOnError(error: String) {
        super.showOnError(error)
        swipeHome.dimiss()
        if (NetworkHelper.isOnline()) {
            containerOffline.gone()
        } else if (movieAdapter.itemCount == 0) {
            containerOffline.visible()
        }

    }

    override fun onSuccessGetDetailsMovie(movie: Movie) {
    }


}
