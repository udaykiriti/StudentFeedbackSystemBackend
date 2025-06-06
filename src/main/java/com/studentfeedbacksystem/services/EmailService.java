package com.studentfeedbacksystem.services;

import com.studentfeedbacksystem.models.FeedbackForm;
import com.studentfeedbacksystem.repositories.UserRepository;
import com.studentfeedbacksystem.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UserRepository userRepository;

    public void sendPasswordResetEmail(String toEmail, String resetLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Password Reset Request");
        message.setText("To reset your password, click the link below:\n" + resetLink);
        emailSender.send(message);
    }

    public void sendNewFormNotification(FeedbackForm form) {
        // Fetch all users to send notification
        List<User> users = userRepository.findAll();

        // Prepare email message
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("New Feedback Form Available: " + form.getTitle());
        
        // Construct email body
        String emailBody = buildEmailBody(form);

        // Send to each user
        for (User user : users) {
            message.setTo(user.getEmail());
            message.setText(emailBody);
            emailSender.send(message);
        }
    }

    private String buildEmailBody(FeedbackForm form) {
        StringBuilder body = new StringBuilder();
        body.append("A new feedback form is now available!\n\n");
        body.append("Title: ").append(form.getTitle()).append("\n");
        body.append("Description: ").append(form.getDescription()).append("\n");
        body.append("Closing Date: ").append(form.getClosingDate()).append("\n");
        
        if (form.isUrgent()) {
            body.append("\nNOTE: This form is marked as URGENT. Please complete it as soon as possible.\n");
        }
        
        body.append("\nPlease log in to the Student Feedback System to provide your feedback.");
        
        return body.toString();
    }
}