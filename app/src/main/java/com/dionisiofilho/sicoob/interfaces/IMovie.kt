package com.dionisiofilho.sicoob.interfaces

import com.dionisiofilho.sicoob.application.bases.BaseView
import com.dionisiofilho.sicoob.model.Movie

interface IMovie : BaseView {

    fun onSuccesGetMovie(movies: List<Movie>)
    fun onSuccessGetDetailsMovie(movie: Movie)
    fun onSuccessSearchMovie(movies: List<Movie>)
}