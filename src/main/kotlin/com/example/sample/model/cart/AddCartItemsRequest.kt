package com.example.sample.model.cart

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Min

data class AddCartItemsRequest(
    @JsonProperty("item_id")
    val itemId: Int,

    @Min(value = 1, message = "Cannot have negative quantity")
    val quantity: Int = 1
)
