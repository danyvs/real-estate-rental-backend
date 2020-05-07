package com.example.realestaterentalbackend.service;

import com.example.realestaterentalbackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(User user) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setSubject("Welcome");
        mail.setText("Welcome to our site, " + user.getFirstName() + " " + user.getLastName() + "!\n" +
                "Your email address is: " + user.getEmail() + "\n" +
                "Your password is: " + user.getPassword() + "\n"
        );

        try {
            this.javaMailSender.send(mail);
        } catch (MailException exception) {
            System.err.println(exception.getMessage());
        }

    }
}
