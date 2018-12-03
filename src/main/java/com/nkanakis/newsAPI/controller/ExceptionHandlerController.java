package com.nkanakis.newsAPI.controller;

import com.nkanakis.newsAPI.controller.dto.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<Object> invalidInput(IllegalArgumentException e) {
        log.error("Invalid input", e);
        ExceptionResponse er = ExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .error(e.getMessage())
                .message("Invalid input")
                .build();
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }

    //handle all exceptions
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception e) {
        log.error("General error", e);
        ExceptionResponse er = ExceptionResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .timestamp(LocalDateTime.now())
                .error(e.getMessage())
                .message("General Error")
                .build();
        return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
