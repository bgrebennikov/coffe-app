package com.example.sample.entity

import jakarta.persistence.*

@Entity
@Table(name = "categories")
class CategoryEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "title", nullable = false, unique = true)
    var title: String,

    @Column(name = "description", nullable = true)
    var description: String? = null,
)