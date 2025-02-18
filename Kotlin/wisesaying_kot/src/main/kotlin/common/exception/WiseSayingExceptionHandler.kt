package com.nrkimprogect.thegym.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class WiseSayingExceptionHandler  {

    // ✅ WiseSayingNotFoundException 처리
    @ExceptionHandler(WiseSayingNotFoundException::class)
    fun handleWiseSayingNotFoundException(ex: WiseSayingNotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }

    // ✅ 삭제 실패 (500)
    @ExceptionHandler(DeleteFailedException::class)
    fun handleDeleteFailedException(ex: DeleteFailedException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.message)
    }

    // ✅ 기타 예외 처리 (예: 서버 에러)
    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error")
    }
}