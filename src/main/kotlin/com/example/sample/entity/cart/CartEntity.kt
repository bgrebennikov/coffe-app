package com.example.sample.entity.cart

import com.example.sample.entity.UserEntity
import com.example.sample.entity.promo.PromoCodeEntity
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "cart")
class CartEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "cart_items")
    @OneToMany(mappedBy = "cart", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    @JsonManagedReference
    val cartItems: MutableList<CartItemEntity> = mutableListOf(),

    @OneToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinColumn(name = "user_id")
    var user: UserEntity? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promo_code_id")
    @JsonProperty("promo_code")
    var promoCode: PromoCodeEntity? = null

)