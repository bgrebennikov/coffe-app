package com.example.sample.controller

import com.example.sample.model.authentication.AuthenticationRequest
import com.example.sample.model.authentication.AuthenticationResponse
import com.example.sample.model.authentication.RefreshRequest
import com.example.sample.model.authentication.RegisterRequest
import com.example.sample.service.UserService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/auth")
class AuthenticationController(
    private val userService: UserService,
) {

    @ResponseStatus(CREATED)
    @PostMapping("/register")
    fun register(@RequestBody registerRequest: RegisterRequest) = userService.register(registerRequest)

    @ResponseStatus(OK)
    @PostMapping("/authenticate")
    fun authenticate(@RequestBody authenticationRequest: AuthenticationRequest) =
        userService.authenticate(authenticationRequest)

    @ResponseStatus(OK)
    @PostMapping("/refresh")
    fun refresh(@RequestBody refreshRequest: RefreshRequest): AuthenticationResponse =
        userService.refresh(refreshRequest)

}
