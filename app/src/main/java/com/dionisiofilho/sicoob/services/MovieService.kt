package com.dionisiofilho.sicoob.services

import com.dionisiofilho.sicoob.BuildConfig
import com.dionisiofilho.sicoob.application.bases.BaseConnectionService
import com.dionisiofilho.sicoob.application.bases.BasePresenterCallback
import com.dionisiofilho.sicoob.application.callback.ServiceCallback
import com.dionisiofilho.sicoob.application.helpers.SharedPreferenceHelper
import com.dionisiofilho.sicoob.model.Movie
import com.dionisiofilho.sicoob.model.MovieResponse
import com.dionisiofilho.sicoob.rest.MovieRest

class MovieService {

    private val language = SharedPreferenceHelper.getLanguage()
    private val apiKey = BuildConfig.ApiKey

    private fun getInstanceConnection(): MovieRest {
        return BaseConnectionService.instance(MovieRest::class.java)
    }

    fun getPopularMovies(page: Int = 1, basePresenterCallback: BasePresenterCallback<MovieResponse>) {
        getInstanceConnection().getPopularMovies(apiKey, language, page).enqueue(ServiceCallback<MovieResponse>().build(basePresenterCallback))
    }

    fun getDetailMovie(idMovie: Int, basePresenterCallback: BasePresenterCallback<Movie>) {
        getInstanceConnection().getDetailMovie(idMovie, apiKey, language).enqueue(ServiceCallback<Movie>().build(basePresenterCallback))
    }
}