package com.example.sample.entity.cart

import com.example.sample.entity.items.ItemEntity
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import jakarta.validation.constraints.Min

@Entity
@Table(name = "cart_item")
class CartItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    @JsonBackReference
    var cart: CartEntity? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    var item: ItemEntity? = null,

    var quantity: Int = 1,
)