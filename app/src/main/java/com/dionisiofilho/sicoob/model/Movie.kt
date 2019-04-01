package com.dionisiofilho.sicoob.model

import com.dionisiofilho.sicoob.application.bases.BaseModel
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.sql.Date
import java.sql.Time

@JsonIgnoreProperties(ignoreUnknown = true)
class Movie : BaseModel() {

    @JsonProperty("id")
    val id: Int = 0

    @JsonProperty("title")
    val title: String = ""

    @JsonProperty("overview")
    val overview: String = ""

    @JsonProperty("runtime")
    val runtime: Int = 0

    @JsonProperty("vote_average")
    val voteAverage: Float = 0.0f

    @JsonProperty("poster_path")
    var posterPath: String? = null

    @JsonProperty("genres")
    val genres: ArrayList<Genre> = arrayListOf()

    @JsonProperty("release_date")
    val releaseDate: Date? = null
}