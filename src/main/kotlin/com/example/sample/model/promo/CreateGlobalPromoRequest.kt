package com.example.sample.model.promo

import com.example.sample.entity.promo.PromoCalculationType
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class CreateGlobalPromoRequest(
    var code: String,
    val description: String? = null,
    @JsonProperty("calculation_type", required = true)
    val calculationType: PromoCalculationType = PromoCalculationType.PERCENT,
    @JsonProperty("discount_amount", required = true)
    var discountAmount: BigDecimal? = 1.toBigDecimal(),
) : PromoRequestBase()
