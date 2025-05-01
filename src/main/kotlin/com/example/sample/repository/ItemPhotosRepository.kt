package com.example.sample.repository

import com.example.sample.entity.items.ItemImageEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ItemPhotosRepository : JpaRepository<ItemImageEntity, Int> {

}