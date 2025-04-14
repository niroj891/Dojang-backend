package com.dojang.controller;

import com.dojang.model.ContactRequest;
import com.dojang.service.DojangEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class ContactController {

    @Autowired
    private DojangEmailService dojangEmailService;

    @PostMapping("/api/contact")
    public String handleContactForm(@RequestBody ContactRequest contactRequest) {
        try {
            dojangEmailService.sendContactEmail(contactRequest);
            return "Message sent successfully!";
        } catch (Exception e) {
            return "Error sending message: " + e.getMessage();
        }
    }

    /*

    import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public String handleContactForm(@RequestBody ContactRequest contactRequest) {
        try {
            emailService.sendContactEmail(contactRequest);
            return "Message sent successfully!";
        } catch (Exception e) {
            return "Error sending message: " + e.getMessage();
        }
    }
}
     */
}
