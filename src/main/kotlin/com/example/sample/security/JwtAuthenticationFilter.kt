package com.example.sample.security

import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService,
    private val userDetailsService: UserDetailsService,

    ) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val log = LoggerFactory.getLogger(JwtAuthenticationFilter::class.java.name)

        val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        val lengthOfBearerWithSpace = 7
        val jwtToken = authHeader.substring(lengthOfBearerWithSpace)

        try {
            val username = jwtService.extractUsername(jwtToken)

            log.info("jwt: $jwtToken\nusername: $username")

            if (SecurityContextHolder.getContext().authentication == null) {
                val userDetails = userDetailsService.loadUserByUsername(username)

                if (jwtService.isAccessTokenValid(jwtToken, userDetails)) {
                    val authenticationToken = UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.authorities
                    )
                    authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = authenticationToken
                }
            }
        } catch (e: ExpiredJwtException) {
            log.warn("JWT expired", e)
            response.status = 401
            response.writer.write("Token expired")
            response.writer.flush()
            return
        } catch (e: Exception) {
            log.error("Exception occurred", e)
            response.status = 401
            response.writer.write("Token invalid")
            response.writer.flush()
            return
        }
        filterChain.doFilter(request, response)

    }
}
