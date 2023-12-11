package com.lawzoom.companyservice.controller.mail;


import com.lawzoom.companyservice.config.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-email")
    public String sendEmail() {
        String to = "kaushlendra.pratap@corpseed.com";
        String subject = "Test Email";
        String body = "This is a test email from your Spring Boot application.";

        emailService.sendEmail(to, subject, body);

        return "Email sent successfully!";
    }
}