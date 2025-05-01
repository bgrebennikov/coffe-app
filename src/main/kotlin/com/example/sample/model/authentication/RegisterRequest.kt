package com.example.sample.model.authentication

import com.fasterxml.jackson.annotation.JsonProperty

data class RegisterRequest(
    val email: String?,
    val password: String,
    @JsonProperty("first_name")
    val firstName: String,
    val phone: String,
)
