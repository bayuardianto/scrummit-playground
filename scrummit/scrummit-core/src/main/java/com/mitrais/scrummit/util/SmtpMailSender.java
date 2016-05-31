package com.mitrais.scrummit.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;


@Component
public class SmtpMailSender {

    @Autowired
    private MailSender mailSender;

    public void send(String to, String subject, String body) throws MailException {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setTo(to);
        message.setText(body);

        mailSender.send(message);

    }
}