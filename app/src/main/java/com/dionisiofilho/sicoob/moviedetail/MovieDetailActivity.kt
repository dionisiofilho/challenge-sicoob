package com.dionisiofilho.sicoob.moviedetail

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.dionisiofilho.sicoob.R
import com.dionisiofilho.sicoob.adapters.GenreAdapter
import com.dionisiofilho.sicoob.application.bases.BaseActivity
import com.dionisiofilho.sicoob.application.helpers.ImageHelper
import com.dionisiofilho.sicoob.application.helpers.ResourcesHelper
import com.dionisiofilho.sicoob.application.widgets.Loading
import com.dionisiofilho.sicoob.enums.ImageSize
import com.dionisiofilho.sicoob.extensions.formatHoursAndMinutes
import com.dionisiofilho.sicoob.extensions.getDatePtBr
import com.dionisiofilho.sicoob.extensions.gone
import com.dionisiofilho.sicoob.extensions.visible
import com.dionisiofilho.sicoob.interfaces.IMovie
import com.dionisiofilho.sicoob.model.Movie
import com.dionisiofilho.sicoob.presenters.MoviePresenter

class MovieDetailActivity : BaseActivity(), IMovie {

    companion object {
        val idMovie: String = "idMovie"
    }

    private lateinit var container: ConstraintLayout
    private lateinit var title: TextView
    private lateinit var runtime: TextView
    private lateinit var genres: RecyclerView
    private lateinit var note: TextView
    private lateinit var overview: TextView
    private lateinit var imageMovie: ImageView
    private lateinit var releaseDate: TextView
    private lateinit var toolbarDetail: Toolbar

    private val moviePresenter: MoviePresenter by lazy {
        MoviePresenter(this, this)
    }

    private val genreAdapter: GenreAdapter by lazy {
        GenreAdapter()
    }

    private val loading: Loading by lazy {
        Loading(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        initViews()

        intent.extras?.let { bundle ->

            val idMovie = bundle[idMovie] as Int

            moviePresenter.getDetailMovie(idMovie)
            container.gone()
            loading.show()
        } ?: run {
            finishWithSlideAnimation()
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        configureToolbar(ResourcesHelper.getAppString(R.string.detail))
        configureAdapterGenre()
    }

    private fun configureAdapterGenre() {
        genres.layoutManager = GridLayoutManager(this, 2)
        genres.adapter = genreAdapter
    }

    private fun configureToolbar(title: String) {
        setSupportActionBar(toolbarDetail)
        supportActionBar?.let {
            it.title = title
        }
    }

    private fun initViews() {
        container = findViewById(R.id.container_detail)
        title = findViewById(R.id.tv_title_movie)
        runtime = findViewById(R.id.tv_runtime_movie)
        genres = findViewById(R.id.lv_genre_movie)
        overview = findViewById(R.id.tv_synopsis_movie)
        imageMovie = findViewById(R.id.iv_movie_detail_image)
        toolbarDetail = findViewById(R.id.default_toolbar)
        note = findViewById(R.id.tv_note_movie)
        releaseDate = findViewById(R.id.tv_release_date_movie)
    }


    private fun updateUI(movie: Movie) {
        title.text = movie.title
        runtime.text = movie.runtime.formatHoursAndMinutes()
        overview.text = movie.overview
        note.text = movie.voteAverage.toString()
        releaseDate.text = movie.releaseDate?.getDatePtBr() ?: ""

        movie.posterPath?.let {
            ImageHelper.getImageFromMovie(it, imageMovie, ImageSize.W342)
        }

        genreAdapter.addAll(movie.genres)

        container.visible()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        return try {
            menuInflater.inflate(R.menu.menu_detail, menu)
            true
        } catch (e: Exception) {
            false
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> {
                finishWithSlideAnimation()
            }
        }

        return false
    }

    override fun onSuccesGetMovie(movies: ArrayList<Movie>) {
    }

    override fun onSuccessGetDetailsMovie(movie: Movie) {
        loading.hide()
        updateUI(movie)
    }

}
