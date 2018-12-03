package com.nkanakis.newsAPI.controller.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Builder
public class ExceptionResponse {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String error;
    private String message;
}
