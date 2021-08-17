package com.adrian.notification.service;

import com.adrian.notification.model.Message;
import com.adrian.notification.model.dto.EmailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
class EmailSenderImpl implements EmailSender {

    private final JavaMailSender javaMailSender;

    public EmailSenderImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmails(Message message) {
        String title = "Pamiętaj o kursie: " + message.getCourseName();
        String content = makeContent(message);
        message.getEmails().forEach(email-> {
            try {
                sendEmail(email, title, content);
            } catch (MessagingException e) {
                log.error("Notyfikacja się nie wysłała!", e);
            }
        });

    }

    @Override
    public void sendEmail(EmailDto emailDto) throws MessagingException {
        sendEmail(emailDto.getTo(), emailDto.getTitle(), emailDto.getContent());
    }


    private void sendEmail(String to, String title, String content) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(title);
        mimeMessageHelper.setText(content,false);
        javaMailSender.send(mimeMessage);
    }

    public String makeContent(Message message) {
        StringBuilder content = new StringBuilder();
        content.append("Witaj!\n");
        content.append("Pamiętaj, że kurs ");
        content.append(message.getCourseName());
        content.append(" zaczyna się: ");
        content.append(message.getStartDate().toString()).append("\n");
        content.append("Proszę wstawić się o 15 minut wcześniej").append("\n");
        content.append("Opis kursu to: ");
        content.append(message.getCourseDescription()).append("\n");
        content.append( "Kurs kończy się o: ");
        content.append(message.getEndDate());
        content.append("\n");
        content.append("Czekamy na Ciebie!");
        return content.toString();
    }
}
