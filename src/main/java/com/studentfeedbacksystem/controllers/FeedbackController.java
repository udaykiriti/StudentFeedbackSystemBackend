package com.studentfeedbacksystem.controllers;

import com.studentfeedbacksystem.models.FeedbackForm;
import com.studentfeedbacksystem.models.Notification;
import com.studentfeedbacksystem.services.FeedbackService;
import com.studentfeedbacksystem.services.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/admin/feedback/forms")
    public FeedbackForm createForm(@RequestBody FeedbackForm form) {
        FeedbackForm savedForm = feedbackService.createForm(form);
        notificationService.sendNewFormNotifications(savedForm);
        return savedForm;
    }

    @GetMapping("/student/feedback/notifications")
    public ResponseEntity<List<Notification>> getActiveNotifications() {
        // Log the notifications for debugging
        List<Notification> activeNotifications = notificationService.getActiveNotifications();
        System.out.println("Active Notifications Count: " + activeNotifications.size());
        return ResponseEntity.ok(activeNotifications);
    }
    @GetMapping("/admin/notifications")
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }
}