package com.capgemini.wsb.fitnesstracker.mail.internal;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class EmailSenderImpl implements EmailSender {

    private final JavaMailSender mailSender;

        @Override
        @Async
        public void send(EmailDto email) {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("fitnesstracker@wsb.com");
            message.setTo(email.toAddress());
            message.setSubject(email.subject());
            message.setText(email.content());

            try {
                log.info("Sending the email {}", email);
                mailSender.send(message);
            } catch (Exception e) {
                log.error("Failed to send email to {}, exception: {}", email.toAddress(), e.getMessage());
            }
    }
}
