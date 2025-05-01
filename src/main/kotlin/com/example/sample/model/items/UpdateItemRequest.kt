package com.example.sample.model.items

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class UpdateItemRequest(
    var title: String? = null,
    var description: String? = null,
    var cost: BigDecimal? = null,
    @JsonProperty("category")
    var categoryId: Long? = null,
    @JsonProperty("is_draft")
    var isDraft: Boolean? = null,

)
