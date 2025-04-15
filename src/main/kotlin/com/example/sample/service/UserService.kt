package com.example.sample.service

import com.example.sample.entity.UserEntity
import com.example.sample.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getProfile(userPrincipal: UserDetails): UserEntity {
        return userRepository.findByUsername(userPrincipal.username)
            ?: throw UsernameNotFoundException("User not found")
    }

}