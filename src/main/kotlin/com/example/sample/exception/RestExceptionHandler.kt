package com.example.sample.exception

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus.*
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.multipart.support.MissingServletRequestPartException

@RestControllerAdvice
class RestExceptionHandler {

    val log: Logger = LoggerFactory.getLogger(RestExceptionHandler::class.java.name)

    @ExceptionHandler
    @ResponseStatus(CONFLICT)
    fun handleRegisteredException(e: RegisteredException): ResponseError {
        log.error(e.message, e)
        return ResponseError.ALREADY_REGISTERED
    }

    @ExceptionHandler
    @ResponseStatus(UNAUTHORIZED)
    fun handleBadCredentialsException(e: BadCredentialsException): ResponseError {
        log.error(e.message, e)
        return ResponseError.BAD_CREDENTIALS
    }

    @ExceptionHandler
    @ResponseStatus(CONFLICT)
    fun handleAlreadyExistsItem(e: ItemAlreadyExistsException): Map<String, String> {
        return mapOf(
            "error" to e.message.toString(),
        )
    }

    @ExceptionHandler
    @ResponseStatus(NOT_FOUND)
    fun handleNotFoundException(e: NotFoundException): Map<String, String> {
        return mapOf("error" to e.message.toString())
    }

    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    fun handleBadRequest(e: BadRequestException): Map<String, String> {
        return mapOf("error" to e.message.toString())
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleMissingServletRequestParameterException(e: MissingServletRequestParameterException): Map<String, String> {
        return mapOf("error" to e.message)
    }

    @ExceptionHandler(MissingServletRequestPartException::class)
    fun handleMissingServletRequestPartException(e: MissingServletRequestPartException): Map<String, String> {
        return mapOf("error" to e.message.toString())
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): Map<String, String> {
        return mapOf("error" to e.message.toString())
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    @ResponseStatus(CONFLICT)
    fun handleDataIntegrityViolation(ex: DataIntegrityViolationException): Map<String, String> {
        val message = if (ex.message?.contains("unique_code") == true) {
            "Такой промокод уже существует"
        } else {
            "Нарушение ограничений базы данных: ${ex.message}"
        }
        return mapOf("error" to message)
    }

    @ExceptionHandler(NonUniqueFieldException::class)
    fun handleNonUniqueFieldException(e: NonUniqueFieldException): Map<String, String> {
        return mapOf("error" to e.message.toString())
    }


}

class RegisteredException : Exception()

@ResponseStatus(CONFLICT)
class ItemAlreadyExistsException : RuntimeException("Item already exists")

@ResponseStatus(BAD_REQUEST)
class NonUniqueFieldException(field: String) : RuntimeException("field $field must be unique")

@ResponseStatus(NOT_FOUND)
class NotFoundException(override val message: String?) : RuntimeException(message)

@ResponseStatus(BAD_REQUEST)
class BadRequestException(override val message: String?) : RuntimeException(message)
