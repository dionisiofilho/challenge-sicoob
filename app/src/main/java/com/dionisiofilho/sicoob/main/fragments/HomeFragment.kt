package com.dionisiofilho.sicoob.main.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
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

class HomeFragment : BaseFragment(), IMovie {

    private var loading: Boolean = true
    private lateinit var searchView: SearchView
    private lateinit var recyclerViewMovie: RecyclerView

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
        getMovies()
    }

    private fun configureSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { search ->
                    moviePresenter.searchMovie(search = search)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }


    private fun initViews() {
        view?.let {
            recyclerViewMovie = it.findViewById(R.id.rv_main_movies)
            searchView = it.findViewById(R.id.sv_movie)
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
        moviePresenter.getMovies(page)
    }

    private fun onClickMovieAdapter(movie: Movie) {
        val intentDetail = Intent(requireContext(), MovieDetailActivity::class.java)
        intentDetail.putExtra(MovieDetailActivity.IDMovie, movie.id)
        startActivityWithAnimation(intentDetail)
    }


    override fun onSuccessSearchMovie(movies: List<Movie>) {
        movieAdapter.addMovies(movies)
    }

    override fun onSuccesGetMovie(movies: List<Movie>) {
        movieAdapter.addMovies(movies, page == 1)

        loading = !(movies.isEmpty() && this.page != 0)
        this.page++
    }

    override fun onSuccessGetDetailsMovie(movie: Movie) {
    }


}
