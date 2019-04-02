package com.dionisiofilho.sicoob.moviedetail

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.dionisiofilho.sicoob.BuildConfig
import com.dionisiofilho.sicoob.R
import com.dionisiofilho.sicoob.adapters.GenreAdapter
import com.dionisiofilho.sicoob.application.bases.BaseActivity
import com.dionisiofilho.sicoob.application.helpers.ImageHelper
import com.dionisiofilho.sicoob.application.helpers.ResourcesHelper
import com.dionisiofilho.sicoob.application.helpers.ToastHelper
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
        val IDMovie: String = "IDMovie"
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

    private lateinit var menu: Menu
    private lateinit var movie: Movie

    private val idMovie: Int by lazy {
        intent.extras?.getInt(IDMovie) as Int
    }

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
        getDetailMovie()
    }


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        configureToolbar(ResourcesHelper.getAppString(R.string.detail))
        configureAdapterGenre()
    }

    override fun onResume() {
        super.onResume()

    }


    private fun getDetailMovie() {

        if (idMovie != 0) {
            moviePresenter.getDetailMovie(idMovie)
            container.gone()
            loading.show()
        } else {
            finishWithSlideAnimation()
        }

    }

    private fun checkMovieIsFavorite() {
        val menuItem = menu.findItem(R.id.act_set_favorite)
        moviePresenter.movieIsFavorite(idMovie) { contains ->
            if (contains) {
                menuItem?.let { mi ->
                    mi.icon = ResourcesHelper.getDrawable(R.drawable.ic_favorite_full)
                }
            }
            this.movie.isFavorite = contains
        }
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


    private fun updateUI() {
        title.text = this.movie.title
        runtime.text = this.movie.runtime.formatHoursAndMinutes()
        overview.text = this.movie.overview
        note.text = this.movie.voteAverage.toString()
        releaseDate.text = this.movie.releaseDate?.getDatePtBr() ?: ""

        this.movie.posterPath?.let {
            ImageHelper.getImageFromMovie(it, imageMovie, ImageSize.W342)
        }

        genreAdapter.addAll(this.movie.genres)

        container.visible()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        try {
            menuInflater.inflate(R.menu.menu_detail, menu)

            menu?.let {
                this.menu = it
            }
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> {
                finishWithSlideAnimation()
            }
            R.id.act_share -> {
                shareMovie()
            }

            R.id.act_set_favorite -> {

                if (this.movie.isFavorite) {
                    moviePresenter.removeFavoriteDatabase(this.movie) { success ->
                        if (success) {
                            ToastHelper.showToastLong(R.string.favorite_delete_success)
                            item.icon = ResourcesHelper.getDrawable(R.drawable.ic_favorite_border)
                        } else {
                            ToastHelper.showToastLong(R.string.favorite_fail)
                        }
                        this.movie.isFavorite = success
                    }
                } else {
                    moviePresenter.setFavoriteMovie(this.movie) { success ->
                        if (success) {
                            ToastHelper.showToastLong(R.string.favorite_success)
                            item.icon = ResourcesHelper.getDrawable(R.drawable.ic_favorite_full)
                        } else {
                            ToastHelper.showToastLong(R.string.favorite_fail)
                        }
                        this.movie.isFavorite = success
                    }

                }

            }
        }

        return false
    }

    private fun shareMovie() {
        intent.extras?.let { bundle ->

            val idMovie = bundle[IDMovie] as Int

            Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, BuildConfig.BaseURLMovie + idMovie)
                type = "text/plain"
                startActivity(this)
            }


        } ?: run {
            ToastHelper.showToastLong(ResourcesHelper.getAppString(R.string.error_share_movie))
        }
    }

    override fun onSuccesGetMovie(movies: List<Movie>) {
    }

    override fun onSuccessGetDetailsMovie(movie: Movie) {
        this.movie = movie
        loading.hide()
        checkMovieIsFavorite()
        updateUI()
    }
}
