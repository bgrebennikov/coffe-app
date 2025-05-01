package com.example.sample.controller.items

import com.example.sample.entity.items.ItemEntity
import com.example.sample.model.items.InsertItemRequest
import com.example.sample.model.items.UpdateItemRequest
import com.example.sample.service.ItemService
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/items")
class ItemsController(
    private val itemService: ItemService
) {


    @GetMapping
    fun getItems(): List<ItemEntity> {
        return itemService.getItems()
    }


    @GetMapping("/search")
    @Transactional(readOnly = true)
    fun searchByTitle(
        @RequestParam(value = "title", required = false) title: String?,
    ): List<ItemEntity> {
        return title?.let { itemService.searchByTitle(it) }.orEmpty()
    }

    @PostMapping("/add")
    fun addItem(@RequestBody item: InsertItemRequest): ItemEntity {
        return itemService.insertItem(item)
    }


    @PatchMapping("/{id}")
    fun updateItem(@PathVariable id: Long, @RequestBody item: UpdateItemRequest): ItemEntity {
        return itemService.updateItem(id, item)
    }

}