package com.example.sample.model.promo

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.FutureOrPresent
import java.math.BigDecimal
import java.time.LocalDateTime

open class PromoRequestBase(
    @JsonProperty(value = "min_quantity")
    var minQuantity: Int? = 1,

    @JsonProperty(value = "min_order_total")
    var minOrderTotal: BigDecimal = 300.toBigDecimal(),


    @JsonProperty(value = "valid_from")
    var validFrom: LocalDateTime? = LocalDateTime.now(),

    @JsonProperty(value = "valid_to")
    @field:FutureOrPresent(message = "Дата окончания акции не может быть назначена ранее текущего времени")
    var validTo: LocalDateTime? = null,
)