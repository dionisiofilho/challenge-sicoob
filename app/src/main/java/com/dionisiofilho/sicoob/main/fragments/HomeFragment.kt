package com.dionisiofilho.sicoob.main.fragments


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewPager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dionisiofilho.sicoob.R
import com.dionisiofilho.sicoob.adapters.MovieAdapter
import com.dionisiofilho.sicoob.adapters.SlideAdapter
import com.dionisiofilho.sicoob.application.bases.BaseFragment
import com.dionisiofilho.sicoob.interfaces.IMovie
import com.dionisiofilho.sicoob.model.Movie
import com.dionisiofilho.sicoob.moviedetail.MovieDetailActivity
import com.dionisiofilho.sicoob.presenters.MoviePresenter
import java.util.*

class HomeFragment : BaseFragment(), IMovie {

    private lateinit var viewPagerMain: ViewPager
    private lateinit var recyclerViewMovie: RecyclerView

    private var page: Int = 1

    private val slideAdapter: SlideAdapter by lazy {
        SlideAdapter(fragmentManager)
    }

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
        getMovies()
    }


    private fun initViews() {
        view?.let {
            recyclerViewMovie = it.findViewById(R.id.rv_main_movies)
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
                        getMovies()
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
        intentDetail.putExtra(MovieDetailActivity.idMovie, movie.id)
        startActivityWithAnimation(intentDetail)
    }

    private fun convigureViewPager() {

        val urlImage = "http://api-hom.contactcloud.dionisiofilho.com/images/icon_new.png"

        slideAdapter.addUrl(urlImage)
        slideAdapter.addUrl(urlImage)
        slideAdapter.addUrl(urlImage)
        slideAdapter.addUrl(urlImage)
        slideAdapter.addUrl(urlImage)

        viewPagerMain.adapter = slideAdapter

        var currentPage = 0
        val handler = Handler()

        val runnable = Runnable {
            if (currentPage == slideAdapter.count) {
                currentPage = 0
            }
            viewPagerMain.setCurrentItem(currentPage++, true)
        }

        Timer().apply {
            schedule(object : TimerTask() {
                override fun run() {
                    handler.post(runnable)
                }

            }, 3000, 3000)
        }
    }

    override fun onSuccesGetMovie(movies: ArrayList<Movie>) {

        if (page == 1) {
            movieAdapter.addMovies(movies)
        } else {
            movieAdapter.addMovies(movies, false)

        }
        this.page++
    }

    private fun clearList() {
        movieAdapter.clear()
    }

    override fun onSuccessGetDetailsMovie(movie: Movie) {
    }


}
