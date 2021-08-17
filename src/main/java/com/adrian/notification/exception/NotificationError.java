package com.adrian.notification.exception;

import org.springframework.http.HttpStatus;

public enum NotificationError {
    EXCHANGE_IS_EMPTY("The exchange is empty", HttpStatus.NOT_FOUND);

    private String message;
    private HttpStatus status;

    NotificationError(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
