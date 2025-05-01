package com.example.sample.repository

import com.example.sample.entity.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CategoriesRepository : JpaRepository<CategoryEntity, Long> {


}