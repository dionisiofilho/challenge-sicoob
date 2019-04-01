package com.dionisiofilho.sicoob.model

import com.dionisiofilho.sicoob.application.bases.BaseModel
import com.fasterxml.jackson.annotation.JsonProperty

class MovieResponse : BaseModel() {

    @JsonProperty("page")
    val page: Int = 0

    @JsonProperty("total_results")
    val totalResults: Int = 0

    @JsonProperty("total_pages")
    val totalPages: Int = 0

    @JsonProperty("results")
    val movies: ArrayList<Movie> = arrayListOf()


}