package com.example.sample.entity.items

import com.example.sample.entity.CategoryEntity
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import java.math.BigDecimal

@Entity
@Table(name = "items")
class ItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long = 0,

    @Column(name = "title", nullable = false)
    var title: String = "",

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    var description: String? = null,

    @Column(name = "cost", nullable = false, precision = 10, scale = 2)
    @Min(value = 1, message = "value can't be negative or zero")
    var cost: BigDecimal = 100.00.toBigDecimal(),

    @Column(name = "is_draft", nullable = false)
    @JsonProperty("is_draft")
    var isDraft: Boolean = true,

    @JsonManagedReference
    @OneToMany(mappedBy = "item", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    var photos: List<ItemImageEntity> = listOf(),

    @ManyToOne(cascade = [CascadeType.DETACH])
    var category: CategoryEntity? = null

)