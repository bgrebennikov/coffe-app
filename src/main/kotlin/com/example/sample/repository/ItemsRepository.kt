package com.example.sample.repository

import com.example.sample.entity.items.ItemEntity
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ItemsRepository : JpaRepository<ItemEntity, Long> {

    fun findByTitle(title: String): Optional<ItemEntity>

    fun findAllByTitleContainingIgnoreCase(title: String): List<ItemEntity>

    override fun findById(itemId: Long): Optional<ItemEntity>
}