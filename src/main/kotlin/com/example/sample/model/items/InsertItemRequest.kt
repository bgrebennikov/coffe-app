package com.example.sample.model.items

import java.math.BigDecimal

data class InsertItemRequest(
    val title: String,
    val description: String? = null,
    val cost: BigDecimal = 100.00.toBigDecimal()
)
