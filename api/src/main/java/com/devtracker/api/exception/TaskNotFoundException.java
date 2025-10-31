package com.devtracker.api.exception;

public class TaskNotFoundException extends RuntimeException {
    public String errorCode;

    public TaskNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
