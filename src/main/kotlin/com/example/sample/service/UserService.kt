package com.example.sample.service

import com.example.sample.entity.UserEntity
import com.example.sample.exception.RegisteredException
import com.example.sample.model.authentication.AuthenticationRequest
import com.example.sample.model.authentication.AuthenticationResponse
import com.example.sample.model.authentication.RefreshRequest
import com.example.sample.model.authentication.RegisterRequest
import com.example.sample.repository.UserRepository
import com.example.sample.security.JwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager
) {
    fun register(registerRequest: RegisterRequest): AuthenticationResponse {

        if (userRepository.findByUsernameOrEmail(registerRequest.username, registerRequest.email) != null) {
            throw RegisteredException()
        }

        val user = UserEntity(
            email = registerRequest.email,
            username = registerRequest.username,
            password = passwordEncoder.encode(registerRequest.password)
        )

        userRepository.save(user)

        val token = jwtService.generateAccessToken(user)
        val refreshToken = jwtService.generateRefreshToken(user)

        return AuthenticationResponse(token, refreshToken)
    }

    fun authenticate(authenticationRequest: AuthenticationRequest): AuthenticationResponse {

        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authenticationRequest.username,
                authenticationRequest.password
            )
        )

        val user = userRepository.findByUsername(authenticationRequest.username)

        return AuthenticationResponse(
            jwtService.generateAccessToken(user!!),
            jwtService.generateRefreshToken(user),
        )
    }

    fun refresh(refreshTokenRequest: RefreshRequest): AuthenticationResponse {
        if (!jwtService.isRefreshTokenValid(refreshTokenRequest.refreshToken)) {
            throw BadCredentialsException("Invalid refresh token")
        }

        val username = jwtService.extractUsername(refreshTokenRequest.refreshToken)
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("User not found")

        return AuthenticationResponse(
            jwtService.generateAccessToken(user),
            jwtService.generateRefreshToken(user),
        )

    }
}
