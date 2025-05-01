package com.example.sample.service

import com.example.sample.entity.CategoryEntity
import com.example.sample.exception.ItemAlreadyExistsException
import com.example.sample.exception.NotFoundException
import com.example.sample.model.categories.InsertCategoryRequest
import com.example.sample.model.categories.UpdateCategoryRequest
import com.example.sample.repository.CategoriesRepository
import jakarta.validation.ValidationException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service


@Service
class CategoryService(
    private val categoriesRepository: CategoriesRepository
) {

    fun insertCategory(category: InsertCategoryRequest): CategoryEntity {
        try {
            return categoriesRepository.save(
                CategoryEntity(
                    title = category.title,
                    description = category.description
                )
            )
        } catch (e: DataIntegrityViolationException) {
            throw ItemAlreadyExistsException()
        }
    }

    fun findAllCategories(): List<CategoryEntity> {
        return categoriesRepository.findAll()
    }

    fun updateById(id: Long, category: UpdateCategoryRequest): CategoryEntity {
        val cat: CategoryEntity = categoriesRepository.findById(id)
            .orElseThrow {
                NotFoundException("Категория не найдена")
            }

        cat.title = category.title ?: cat.title
        cat.description = category.description ?: cat.description

        return categoriesRepository.save(cat)
    }


}