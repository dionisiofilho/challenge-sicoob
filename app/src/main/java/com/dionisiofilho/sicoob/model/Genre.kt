package com.dionisiofilho.sicoob.model

import com.dionisiofilho.sicoob.application.bases.BaseModel
import com.fasterxml.jackson.annotation.JsonProperty

class Genre : BaseModel() {
    @JsonProperty("id")
    val id: Int = 0

    @JsonProperty("name")
    val name: String = ""
}