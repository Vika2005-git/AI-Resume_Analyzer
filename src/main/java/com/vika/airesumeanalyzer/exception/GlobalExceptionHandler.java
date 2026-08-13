package com.vika.airesumeanalyzer.exception;

import com.vika.airesumeanalyzer.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResumeNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResumeNotFound(
            ResumeNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiResponse(
                        ex.getMessage(),
                        "Failed",
                        "1.0"
                )
        );
    }
}