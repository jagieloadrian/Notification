package com.adrian.notification.api;

import com.adrian.notification.model.dto.EmailDto;
import com.adrian.notification.service.EmailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequestMapping("/notification")
@Slf4j
class EmailController {

    private final EmailSender emailSender;


    EmailController(EmailSender emailSender) {

        this.emailSender = emailSender;
    }

    @PostMapping("/email")
    public ResponseEntity<String> sendEmail(@RequestBody @Valid EmailDto emailDto) {
        try {
            emailSender.sendEmail(emailDto);
        } catch (MessagingException e) {
            log.error("Wiadomość do" + emailDto.getTo() + "się nie wysłała!", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Wiadomość do" + emailDto.getTo() + "się nie wysłała!");
        }
        return ResponseEntity.ok("Wysłano email do " + emailDto.getTo());
    }

}
