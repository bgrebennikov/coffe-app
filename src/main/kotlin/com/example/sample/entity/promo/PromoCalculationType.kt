package com.example.sample.entity.promo

import com.fasterxml.jackson.annotation.JsonProperty

enum class PromoCalculationType {
    @JsonProperty("PERCENT")
    PERCENT,

    @JsonProperty("FIXED")
    FIXED,
}