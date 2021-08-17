package com.adrian.notification.service;

import com.adrian.notification.model.Message;
import com.adrian.notification.model.dto.EmailDto;

import javax.mail.MessagingException;

public interface EmailSender {

    void sendEmails(Message message);

    void sendEmail(EmailDto emailDto) throws MessagingException;
}
