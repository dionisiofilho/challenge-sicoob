package com.dionisiofilho.sicoob.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.dionisiofilho.sicoob.application.bases.BaseModel
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.sql.Date

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
class Movie : BaseModel() {


    @JsonProperty("id")
    @PrimaryKey
    var id: Int = 0


    @JsonProperty("title")
    var title: String = ""

    @JsonProperty("overview")
    @Ignore
    val overview: String = ""

    @JsonProperty("runtime")
    @Ignore
    val runtime: Int = 0

    @JsonProperty("vote_average")
    @Ignore
    val voteAverage: Float = 0.0f

    @JsonProperty("poster_path")
    var posterPath: String? = null

    @JsonProperty("genres")
    @Ignore
    val genres: ArrayList<Genre> = arrayListOf()

    @JsonProperty("release_date")
    @Ignore
    val releaseDate: Date? = null

    @Ignore
    var isFavorite: Boolean = false

}


