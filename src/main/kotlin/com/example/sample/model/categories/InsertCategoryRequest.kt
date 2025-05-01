package com.example.sample.model.categories


data class InsertCategoryRequest(
    val title: String,
    val description: String? = null
)
