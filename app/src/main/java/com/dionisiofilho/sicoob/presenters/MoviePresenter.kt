package com.dionisiofilho.sicoob.presenters

import android.content.Context
import android.util.Log
import com.dionisiofilho.sicoob.application.bases.BasePresenter
import com.dionisiofilho.sicoob.application.bases.BasePresenterCallback
import com.dionisiofilho.sicoob.application.helpers.DatabaseHelper
import com.dionisiofilho.sicoob.interfaces.IMovie
import com.dionisiofilho.sicoob.model.Movie
import com.dionisiofilho.sicoob.model.MovieResponse
import com.dionisiofilho.sicoob.services.MovieService

class MoviePresenter(ctx: Context, view: IMovie) : BasePresenter<IMovie>(ctx, view) {

    private val TAG: String = MoviePresenter::class.java.simpleName

    private val movieService = MovieService()

    private val appDatabase by lazy {
        DatabaseHelper.getInstanceDatabase()
    }

    fun getMovies(page: Int) {

        movieService.getPopularMovies(page, object : BasePresenterCallback<MovieResponse>() {
            override fun onSuccess(response: MovieResponse) {
                getView().onSuccesGetMovie(response.movies)
            }

            override fun onError(throwable: Throwable) {
                throwable.message?.let { getView().showOnError(it) }
            }
        })

    }

    fun getDetailMovie(idMovie: Int) {

        movieService.getDetailMovie(idMovie, object : BasePresenterCallback<Movie>() {
            override fun onSuccess(response: Movie) {
                getView().onSuccessGetDetailsMovie(response)
            }

            override fun onError(throwable: Throwable) {
                throwable.message?.let { getView().showOnError(it) }
            }
        })

    }

    fun getFavoriteMovies(moviesFavorite: (movies: List<Movie>) -> Unit) {
        moviesFavorite(appDatabase.movieDao().getAllMovieDatabase())
    }

    fun setFavoriteMovie(movie: Movie, resultQuery: (success: Boolean) -> Unit) {

        try {
            appDatabase.movieDao().inserMovieDatabase(movie)
        } catch (e: Exception) {
            Log.e(TAG, e.message)
            resultQuery(false)
        } finally {
            resultQuery(true)
        }

    }

    fun movieIsFavorite(idMovie: Int, resultQuery: (contains: Boolean) -> Unit) {
        resultQuery(appDatabase.movieDao().verifyMovieIsFavorit(idMovie))
    }

    fun removeFavoriteDatabase(movie: Movie, resultQuery: (success: Boolean) -> Unit) {
        try {
            appDatabase.movieDao().deleteMovieFavorite(movie)
        } catch (e: Exception) {
            Log.e(TAG, e.message)
            resultQuery(false)
        } finally {
            resultQuery(true)
        }
    }

    fun searchMovie(page: Int = 1, search: String) {

        movieService.searchMovie(page, search, object : BasePresenterCallback<MovieResponse>() {
            override fun onSuccess(response: MovieResponse) {
                getView().onSuccesGetMovie(response.movies)
            }

            override fun onError(throwable: Throwable) {
                throwable.message?.let { getView().showOnError(it) }
            }
        })

    }

}