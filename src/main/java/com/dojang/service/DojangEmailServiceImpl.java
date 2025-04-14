package com.dojang.service;

import com.dojang.model.ContactRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class DojangEmailServiceImpl implements DojangEmailService{

    @Autowired
    private JavaMailSender mailSender;

    @Value("${dojang.mailer:nirojpanta96@gmail.com}")
    private String senderAddress;

    SimpleMailMessage message = new SimpleMailMessage();


        public void sendContactEmail(ContactRequest contactRequest) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(contactRequest.getEmail());
            message.setTo(senderAddress);
            message.setSubject("New Contact Message: " + contactRequest.getSubject());

            String emailContent = String.format(
                    "Name: %s\nEmail: %s\n\nMessage:\n%s",
                    contactRequest.getFullName(),
                    contactRequest.getEmail(),
                    contactRequest.getMessage()
            );

            message.setText(emailContent);

            mailSender.send(message);
        }

    }








