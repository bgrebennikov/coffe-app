package com.example.sample.controller

import com.example.sample.service.UserService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/profile")
class ProfileController(
    private val userService: UserService
) {

    @GetMapping
    fun profile(
        @AuthenticationPrincipal user: UserDetails,
    ): UserDetails {
        return userService.getProfile(user)
    }

}