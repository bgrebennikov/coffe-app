package com.example.sample.service.cart

import com.example.sample.entity.UserEntity
import com.example.sample.entity.cart.CartEntity
import com.example.sample.entity.cart.CartItemEntity
import com.example.sample.entity.items.ItemEntity
import com.example.sample.entity.promo.PromoCodeEntity
import com.example.sample.repository.ItemsRepository
import com.example.sample.repository.cart.CartItemRepository
import com.example.sample.repository.cart.CartRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CartService(
    private val cartRepository: CartRepository,
    private val itemsRepository: ItemsRepository,
    private val cartItemRepository: CartItemRepository
) {


    @Transactional
    fun findByUser(userEntity: UserEntity): CartEntity {
        return cartRepository.findByUser(userEntity).orElseGet {
            cartRepository.save(
                CartEntity(
                    user = userEntity,
                )
            )
        }
    }

    @Transactional
    fun addItemToCart(user: UserEntity, item: ItemEntity, quantity: Int): CartEntity {
        val cart = findByUser(user)
        val existingCartItem = cartItemRepository.findByCartAndItem(cart, item)


        if (existingCartItem != null) {
            existingCartItem.quantity += quantity
            cartItemRepository.save(existingCartItem)
            return cart
        }

        val cartItem = CartItemEntity(
            item = item,
            cart = cart,
            quantity = quantity
        )
        val savedCartItem: CartItemEntity = cartItemRepository.save(cartItem)
        cart.cartItems.add(savedCartItem)

        return cart
    }

    @Transactional
    fun applyPromoCode(user: UserEntity, promoCode: PromoCodeEntity): CartEntity {
        val cart = findByUser(user)
        cart.promoCode = promoCode
        return cartRepository.save(cart)
    }


}