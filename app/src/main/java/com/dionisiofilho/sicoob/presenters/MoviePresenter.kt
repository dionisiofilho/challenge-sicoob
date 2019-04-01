package com.dionisiofilho.sicoob.presenters

import android.content.Context
import com.dionisiofilho.sicoob.application.bases.BasePresenter
import com.dionisiofilho.sicoob.application.bases.BasePresenterCallback
import com.dionisiofilho.sicoob.interfaces.IMovie
import com.dionisiofilho.sicoob.model.Movie
import com.dionisiofilho.sicoob.model.MovieResponse
import com.dionisiofilho.sicoob.services.MovieService

class MoviePresenter(ctx: Context, view: IMovie) : BasePresenter<IMovie>(ctx, view) {

    private val movieService = MovieService()

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

        movieService.getDetailMovie(idMovie, object :BasePresenterCallback<Movie>(){
            override fun onSuccess(response: Movie) {
            getView().onSuccessGetDetailsMovie(response)
            }

            override fun onError(throwable: Throwable) {
                throwable.message?.let { getView().showOnError(it) }
            }
        })

    }

}