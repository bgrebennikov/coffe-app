package com.example.sample.controller.promo

import com.example.sample.entity.promo.PromoCodeEntity
import com.example.sample.exception.NonUniqueFieldException
import com.example.sample.model.promo.CreateCategoryPromoRequest
import com.example.sample.model.promo.CreateGlobalPromoRequest
import com.example.sample.service.PromoCodeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/promo")
class PromoController(
    private val promoCodeService: PromoCodeService
) {

    @GetMapping
    fun getPromoCodes(): List<Any> {
        return listOf("Promo", "Codes")
    }

    @PostMapping("/global")
    fun createGlobal(
        @RequestBody promoRequest: CreateGlobalPromoRequest
    ): PromoCodeEntity {
        if (promoCodeService.isPromoExists(promoRequest.code)) {
            throw NonUniqueFieldException(field = "code")
        }
        return promoCodeService.createPromo(promoRequest)
    }

    @PostMapping("/category")
    fun createGlobal(
        @RequestBody promoRequest: CreateCategoryPromoRequest
    ): PromoCodeEntity {
        if (promoCodeService.isPromoExists(promoRequest.code)) {
            throw NonUniqueFieldException(field = "code")
        }
        return promoCodeService.createPromo(promoRequest)
    }


}