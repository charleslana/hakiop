package com.charles.hakiop.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ResourceExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, String>> handleInvalidArgument(CustomException exception) {
        log.info("Handle custom exception: {}", exception.getMessage());
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleInvalidArgument(IllegalArgumentException exception) {
        log.info("Handle illegal argument exception: {}", exception.getMessage());
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }
}
