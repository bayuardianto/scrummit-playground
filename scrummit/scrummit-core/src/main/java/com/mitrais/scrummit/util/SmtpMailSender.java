package com.mitrais.scrummit.util;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


@Component
public class SmtpMailSender {

    @Autowired
    private JavaMailSender mailSender;

    public void send(String to, String subject, String plainTextbody, String htmlBody) throws AddressException ,MailException,
            MessagingException {
    	
    	try {
        MimeMessage mail = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setSubject(subject);
        helper.setTo(to);
        helper.setText(htmlBody, true);

        mailSender.send(mail);
    	}
    	
    	catch(AddressException ex) {
    		System.err.println("Failed to send email. Address is not valid.");
            ex.printStackTrace();
    	}
    	
    	catch (MailException ex) {
            System.err.println("Failed to send email.");
            ex.printStackTrace();
        }
    	
    	catch (MessagingException ex) {
            System.err.println("Failed to send email.");
            ex.printStackTrace();
        }
    }
}
