package com.example.sample.repository

import com.example.sample.entity.promo.PromoCodeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface PromoCodeRepository : JpaRepository<PromoCodeEntity, Long> {

    fun findByCode(code: String): Optional<PromoCodeEntity>

}