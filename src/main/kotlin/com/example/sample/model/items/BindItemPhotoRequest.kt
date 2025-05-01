package com.example.sample.model.items

import com.fasterxml.jackson.annotation.JsonProperty

data class BindItemPhotoRequest(
    @JsonProperty("item_id")
    val itemId : Long,
    val url : String
)
