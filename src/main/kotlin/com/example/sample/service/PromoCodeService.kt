package com.example.sample.service

import com.example.sample.entity.promo.PromoCalculationType
import com.example.sample.entity.promo.PromoCodeEntity
import com.example.sample.entity.promo.PromoScope
import com.example.sample.exception.NonUniqueFieldException
import com.example.sample.exception.NotFoundException
import com.example.sample.model.promo.CreateCategoryPromoRequest
import com.example.sample.model.promo.CreateGlobalPromoRequest
import com.example.sample.repository.CategoriesRepository
import com.example.sample.repository.PromoCodeRepository
import org.springframework.stereotype.Service

@Service
class PromoCodeService(
    private val promoCodeRepository: PromoCodeRepository,
    private val categoriesRepository: CategoriesRepository
) {

    fun findByCode(code: String): PromoCodeEntity {
        return promoCodeRepository.findByCode(code).orElseThrow {
            NotFoundException("Promo code $code not found")
        }
    }

    fun isPromoExists(code: String): Boolean {
        return !promoCodeRepository.findByCode(code).isEmpty
    }

    fun createPromo(promo: CreateCategoryPromoRequest): PromoCodeEntity {

        val category = categoriesRepository.findById(promo.categoryId).orElseThrow {
            NotFoundException("Категория с ID: ${promo.categoryId} не найдена")
        }

        return promoCodeRepository.save(
            PromoCodeEntity(
                code = promo.code,
                description = promo.description,
                scope = PromoScope.CATEGORY_DISCOUNT,
                calculationType = promo.calculationType,
                category = category,
                categoryDiscount = promo.discountAmount,
                minOrderTotal = promo.minOrderTotal,
                minQuantity = promo.minQuantity,
                validFrom = promo.validFrom,
                validTo = promo.validTo,
            )
        )
    }

    fun createPromo(promo: CreateGlobalPromoRequest): PromoCodeEntity {

        return promoCodeRepository.save(
            PromoCodeEntity(
                code = promo.code,
                description = promo.description,
                scope = PromoScope.GLOBAL_DISCOUNT,
                calculationType = promo.calculationType,
                discountGlobal = promo.discountAmount,
                minOrderTotal = promo.minOrderTotal,
                minQuantity = promo.minQuantity,
                validFrom = promo.validFrom,
                validTo = promo.validTo,
            )
        )
    }


}