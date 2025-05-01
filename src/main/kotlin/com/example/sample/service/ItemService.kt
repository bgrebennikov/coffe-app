package com.example.sample.service

import com.example.sample.entity.items.ItemEntity
import com.example.sample.entity.items.ItemImageEntity
import com.example.sample.exception.NotFoundException
import com.example.sample.model.items.InsertItemRequest
import com.example.sample.model.items.UpdateItemRequest
import com.example.sample.repository.CategoriesRepository
import com.example.sample.repository.ItemPhotosRepository
import com.example.sample.repository.ItemsRepository
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Service
class ItemService(
    private val itemsRepository: ItemsRepository,
    private val photosRepository: ItemPhotosRepository,
    private val categoriesRepository: CategoriesRepository
) {

    fun bindPhoto(itemId: Long, photoUrl: String): ItemImageEntity {
        val item = itemsRepository.findById(itemId).getOrNull() ?: throw NotFoundException("Товар не найден")

        return photosRepository.save(
            ItemImageEntity(
                url = photoUrl,
                item = item
            )
        )

    }

    fun getItems(): List<ItemEntity> {
        return itemsRepository.findAll()
    }

    fun updateItem(itemId: Long, req: UpdateItemRequest): ItemEntity {
        val item = itemsRepository.findById(itemId).orElseThrow {
            throw NotFoundException("Товар не найден")
        }

        item.title = req.title ?: item.title
        item.description = req.description ?: item.description
        item.cost = req.cost ?: item.cost
        item.isDraft = req.isDraft ?: item.isDraft

        req.categoryId?.let {
            val category = categoriesRepository.findById(it).orElseThrow {
                NotFoundException("Категории с ID ${req.categoryId} не существует")
            }
            item.category = category
        }

        return itemsRepository.save(item)

    }

    fun insertItem(item: InsertItemRequest): ItemEntity {
        return itemsRepository.save(
            ItemEntity(
                title = item.title,
                description = item.description,
                cost = item.cost
            )
        )
    }

    fun findItemById(id: Long): ItemEntity {
        return itemsRepository.findById(id).orElseThrow {
            NotFoundException("Item not found")
        }
    }

    fun searchByTitle(title: String): List<ItemEntity> {
        return itemsRepository.findAllByTitleContainingIgnoreCase(title)
    }


}