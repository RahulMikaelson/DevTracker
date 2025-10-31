package com.devtracker.api.aop;

import com.devtracker.api.dto.ExceptionResponse;
import com.devtracker.api.exception.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ExceptionResponse> taskNotFoundExceptionHandler(TaskNotFoundException exception){
        return  new ResponseEntity<>(new ExceptionResponse(exception.errorCode, exception.getMessage(), Instant.now()), HttpStatus.NOT_FOUND);
    }
}
