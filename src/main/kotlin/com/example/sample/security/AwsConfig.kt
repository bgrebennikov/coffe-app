package com.example.sample.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import java.net.URI

@Configuration
class AwsConfig {

    @Value("\${aws.region}")
    lateinit var region: String

    @Value("\${aws.accessKeyId}")
    lateinit var accessKeyId: String

    @Value("\${aws.secretAccessKey}")
    lateinit var secretAccessKey: String

    @Bean
    fun s3Client(): S3Client {

        val credentials = AwsBasicCredentials.create(accessKeyId, secretAccessKey)
        val provider = StaticCredentialsProvider.create(credentials)

        return S3Client.builder()
            .region(Region.of(region))
            .endpointOverride(
                URI.create("https://storage.yandexcloud.net"),
            )
            .credentialsProvider(provider)
            .build()
    }

}