package com.example.sample.controller

import com.example.sample.entity.CategoryEntity
import com.example.sample.model.categories.InsertCategoryRequest
import com.example.sample.model.categories.UpdateCategoryRequest
import com.example.sample.service.CategoryService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class CategoriesController(
    private val categoryService: CategoryService
) {

    @GetMapping
    fun categories(): List<CategoryEntity> {
        return categoryService.findAllCategories()
    }

    @PostMapping
    fun create(@RequestBody request: InsertCategoryRequest): CategoryEntity? {
        return categoryService.insertCategory(request)
    }

    @PatchMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody cat: UpdateCategoryRequest): CategoryEntity {
        return categoryService.updateById(id, cat)
    }


}