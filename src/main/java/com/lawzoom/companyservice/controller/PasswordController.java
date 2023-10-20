package com.lawzoom.companyservice.controller;

import com.lawzoom.companyservice.model.companyModel.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class PasswordController {

//    @Autowired
//    private JavaMailSender javaMailSender;

//    @Value("${spring.mail.username}")
//    private String fromEmail;
//
//    @Autowired
//    private Company company;

//    @PostMapping("/generateAndSendPassword")
//    public String generateAndSendPassword(@RequestParam String toEmail) {
//        String password = generateRandomPassword();
//        sendPasswordEmail(toEmail, password);
//        return "Password generated and sent successfully!";
//    }

    public String generateRandomPassword() {
        String charKey = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rand = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            password.append(charKey.charAt(rand.nextInt(charKey.length())));
        }

        return password.toString();
    }

//    private void sendPasswordEmail(String toEmail, String password) {
//
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom(fromEmail);
//        mailMessage.setTo(toEmail);
//        mailMessage.setSubject( "Kaushal singh wants you to join" +"Corpeed ERP");
//        mailMessage.setText("Your temporary password is: " + password);
//
//        javaMailSender.send(mailMessage);
//    }

}
