package ru.example.service.error

import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ErrorHandler {

    @ResponseBody
    @ResponseStatus
    @ExceptionHandler(RuntimeException::class)
    fun handle(e: Exception): ErrorResponse {
        logger.error { e }
        return ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            message = e.message
        )
    }

    companion object : KLogging()
}