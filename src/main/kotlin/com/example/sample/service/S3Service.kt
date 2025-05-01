package com.example.sample.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.ObjectCannedACL
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.util.*

@Service
class S3Service(
    private val s3Client: S3Client,
    @Value("\${aws.bucket-name}") private val bucketName: String,
) {

    fun uploadFile(file: MultipartFile): String {
        val key = "uploads/${UUID.randomUUID()}-${file.originalFilename}"

        val putRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .contentType(file.contentType)
            .acl(ObjectCannedACL.PUBLIC_READ)
            .build()

        s3Client.putObject(putRequest, RequestBody.fromBytes(file.bytes))
        return "https://s3.yandexcloud.net/$bucketName/$key"
    }

}