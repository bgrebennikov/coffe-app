package com.example.sample.model.authentication

import com.fasterxml.jackson.annotation.JsonProperty

data class RefreshRequest(
    @JsonProperty("refresh_token")
    val refreshToken: String,
)
