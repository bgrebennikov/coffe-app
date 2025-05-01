package com.example.sample.controller.cart

import com.example.sample.entity.UserEntity
import com.example.sample.entity.cart.CartEntity
import com.example.sample.entity.promo.PromoCodeEntity
import com.example.sample.model.cart.AddCartItemsRequest
import com.example.sample.service.ItemService
import com.example.sample.service.PromoCodeService
import com.example.sample.service.UserService
import com.example.sample.service.cart.CartService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cart")
class CartController(
    private val cartService: CartService,
    private val userService: UserService,
    private val itemService: ItemService,
    private val promoCodeService: PromoCodeService
) {


    @GetMapping
    fun getCart(
        @AuthenticationPrincipal userPrincipal: UserDetails
    ): ResponseEntity<Any> {
        val user = userService.getProfile(userPrincipal)


        return ResponseEntity.ok(
            cartService.findByUser(user)
        )
    }

    @PutMapping
    fun addItemsToCart(
        @RequestBody itemsRequest: AddCartItemsRequest,
        @AuthenticationPrincipal userPrincipal: UserDetails
    ): Any {
        val user: UserEntity = userService.getProfile(userPrincipal)
        val item = itemService.findItemById(itemsRequest.itemId.toLong())

        return cartService.addItemToCart(
            user = user,
            item = item,
            quantity = itemsRequest.quantity
        )
    }

    @PostMapping("/promo-code/{promoCode}")
    fun applyPromoCode(
        @PathVariable promoCode: String,
        @AuthenticationPrincipal userDetails: UserDetails
    ): ResponseEntity<Any> {
        val code: PromoCodeEntity = promoCodeService.findByCode(promoCode)
        val user: UserEntity = userService.getProfile(userDetails)

        return ResponseEntity.ok(
            cartService.applyPromoCode(user, code)
        )

    }

}