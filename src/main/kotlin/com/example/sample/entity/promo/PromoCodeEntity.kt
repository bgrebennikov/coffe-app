package com.example.sample.entity.promo

import com.example.sample.entity.CategoryEntity
import com.example.sample.entity.items.ItemEntity
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "promo_codes")
@JsonInclude(JsonInclude.Include.NON_NULL)
class PromoCodeEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    var id: Long? = null,

    @Column(name = "code", unique = true, nullable = false)
    @JsonProperty("code")
    var code: String,

    @Column(columnDefinition = "TEXT", nullable = true)
    @JsonProperty("description")
    var description: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @JsonProperty("scope")
    var scope: PromoScope,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "calculation_type")
    @JsonProperty("calculation_type")
    var calculationType: PromoCalculationType,


    // GLOBAL DISCOUNT //

    @Column(name = "discount_global", nullable = true)
    @Min(value = 1, message = "discount must be between 1 and 100")
    @Max(value = 100, message = "discount must be between 1 and 100")
    @JsonProperty("discount_global")
    var discountGlobal: BigDecimal? = null,

    // SPECIFIC ITEMS DISCOUNT //

    @OneToMany(cascade = [CascadeType.DETACH], fetch = FetchType.LAZY)
    @Column(name = "for_products", nullable = true)
    @JsonProperty("for_products")
    var forProducts: List<ItemEntity>? = null,

    // CATEGORY DISCOUNT //

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    @JsonProperty("category")
    var category: CategoryEntity? = null,

    @Column(name = "discount_category", nullable = true)
    @Min(value = 1, message = "discount must be positive number")
    @JsonProperty("discount_category")
    var categoryDiscount: BigDecimal? = null,

    // FREE PRODUCT //

    @ManyToOne
    @JoinColumn(name = "free_product_id", nullable = true)
    @JsonProperty("free_product")
    var freeProduct: ItemEntity? = null,

    // REQUIREMENTS //

    @Column(name = "min_quantity")
    @JsonProperty("min_quantity")
    var minQuantity: Int? = 1,

    @Column(name = "min_order_total")
    @JsonProperty("min_order_total")
    var minOrderTotal: BigDecimal,

    @Column(name = "valid_from", nullable = false)
    @JsonProperty("valid_from")
    var validFrom: LocalDateTime? = LocalDateTime.now(),

    @Column(name = "valid_to", nullable = true)
    @JsonProperty("valid_to")
    var validTo: LocalDateTime? = null
)
