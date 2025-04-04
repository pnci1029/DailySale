package com.pro.dailysale.util.exception

import net.minidev.json.JSONObject
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(annotations = [RestController::class])
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(CustomException::class)
    fun handleCustomException(e: CustomException): ResponseEntity<ErrorCodeResult> {
        generateErrorLog(e)
        return errorMessage(e.code, e.bindingErrors, e.data)
    }

    private fun errorMessage(errorCode: ErrorCode, bindingErrors: List<FieldBindingError> = emptyList(), data: Any? = null): ResponseEntity<ErrorCodeResult> {
        val jsonObject = JSONObject()
        jsonObject.put("errorMsg", data)

        return ResponseEntity
            .status(errorCode.httpCode)
            .body(ErrorCodeResult(errorCode, jsonObject, ""))

    }

    private fun generateErrorLog(e: CustomException) {
        logger.error("CustomException 발생: ${e.message}", e)
    }
}