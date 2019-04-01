package com.dionisiofilho.sicoob.rest

import com.dionisiofilho.sicoob.model.Movie
import com.dionisiofilho.sicoob.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieRest {

    @GET("popular")
    fun getPopularMovies(@Query("api_key") apiKey: String, @Query("language") language: String, @Query("page") page: Int): Call<MovieResponse>

    @GET("{id}")
    fun getDetailMovie(@Path("id") idMovie: Int, @Query("api_key") apiKey: String, @Query("language") language: String): Call<Movie>

}