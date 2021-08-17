package com.adrian.notification.exception;

public class NotificationException extends RuntimeException {

    private NotificationError notificationError;

    public NotificationException(NotificationError notificationError){
        this.notificationError = notificationError;
    }

    NotificationError getNotificationError() {
        return notificationError;
    }




}
