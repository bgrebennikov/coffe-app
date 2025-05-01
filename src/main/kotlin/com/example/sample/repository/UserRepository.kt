package com.example.sample.repository

import com.example.sample.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<UserEntity, Int> {
    fun findByPhone(phone: String): UserEntity?
}
