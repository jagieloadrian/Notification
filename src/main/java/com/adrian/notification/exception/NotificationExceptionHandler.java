package com.adrian.notification.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class NotificationExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> handleException(NotificationException e){
        return ResponseEntity.status(e.getNotificationError().getStatus())
                .body(new ErrorInfo(e.getNotificationError().getMessage()));
    }
}
