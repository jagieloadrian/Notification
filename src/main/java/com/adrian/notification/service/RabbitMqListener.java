package com.adrian.notification.service;

import com.adrian.notification.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMqListener {

    private final EmailSender emailSender;

    RabbitMqListener(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @RabbitListener(queues = "enroll_finish")
    public void handleFinishEnroll(Message message) {
        emailSender.sendEmails(message);
    }
}
