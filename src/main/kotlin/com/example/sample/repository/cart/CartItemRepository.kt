package com.example.sample.repository.cart

import com.example.sample.entity.cart.CartEntity
import com.example.sample.entity.cart.CartItemEntity
import com.example.sample.entity.items.ItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CartItemRepository : JpaRepository<CartItemEntity, Long> {

    fun findByCartAndItem(cart: CartEntity, item: ItemEntity): CartItemEntity?

}