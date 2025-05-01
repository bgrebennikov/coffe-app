package com.example.sample.controller

import com.example.sample.entity.items.ItemImageEntity
import com.example.sample.service.ItemService
import com.example.sample.service.S3Service
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/upload")
class UploadController(
    private val s3Service: S3Service,
    private val itemService: ItemService,
) {

    @PostMapping
    fun upload(
        @RequestParam("item_photo") itemPhoto: MultipartFile,
        @RequestParam("item_id") itemId: Long
    ): ResponseEntity<ItemImageEntity> {

        val photoUrl = s3Service.uploadFile(itemPhoto)


        return ResponseEntity.ok(itemService.bindPhoto(itemId, photoUrl))
    }

}