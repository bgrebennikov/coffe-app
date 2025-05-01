package com.example.sample.repository.cart

import com.example.sample.entity.UserEntity
import com.example.sample.entity.cart.CartEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CartRepository : JpaRepository<CartEntity, Long> {

    fun findByUser(user: UserEntity): Optional<CartEntity>

}