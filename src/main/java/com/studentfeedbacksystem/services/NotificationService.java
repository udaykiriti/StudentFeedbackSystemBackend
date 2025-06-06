	package com.studentfeedbacksystem.services;
	
	import com.studentfeedbacksystem.models.FeedbackForm;
	import com.studentfeedbacksystem.models.Notification;
	import com.studentfeedbacksystem.repositories.FeedbackFormRepository;
	import com.studentfeedbacksystem.repositories.NotificationRepository;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	
	import java.time.LocalDateTime;
	import java.util.List;
	import java.util.stream.Collectors;
	
	@Service
	public class NotificationService {
	    @Autowired
	    private FeedbackFormRepository feedbackFormRepository;
	
	    @Autowired
	    private NotificationRepository notificationRepository;
	
	    @Autowired
	    private EmailService emailService;
	
	    public List<FeedbackForm> getNewAndUrgentForms() {
	        LocalDateTime now = LocalDateTime.now();
	        return feedbackFormRepository.findAll().stream()
	            .filter(form -> form.getClosingDate() != null && form.getClosingDate().isAfter(now))
	            .filter(form -> 
	                (form.getCreatedAt() != null && form.getCreatedAt().isAfter(now.minusDays(7))) || 
	                form.isUrgent()
	            )
	            .collect(Collectors.toList());
	    }
	
	    public Notification createNotification(FeedbackForm form) {
	        Notification notification = new Notification();
	        
	        // Safely handle title
	        notification.setTitle(
	            form.getTitle() != null 
	            ? form.getTitle().toString() 
	            : (form.getCustomTitle() != null ? form.getCustomTitle() : "Untitled Form")
	        );
	        
	        // Safely handle description
	        notification.setDescription(
	            form.getDescription() != null 
	            ? form.getDescription() 
	            : "No description provided"
	        );
	        
	        notification.setCreatedAt(LocalDateTime.now());
	        notification.setClosingDate(form.getClosingDate());
	        notification.setUrgent(form.isUrgent());
	
	        return notificationRepository.save(notification);
	    }
	
	    public void sendNewFormNotifications(FeedbackForm form) {
	        // Create a notification
	        Notification notification = createNotification(form);
	
	        // Send email notifications to all registered students
	        try {
	            emailService.sendNewFormNotification(form);
	        } catch (Exception e) {
	            // Log the error or handle it appropriately
	            System.err.println("Failed to send email notifications: " + e.getMessage());
	        }
	    }
	
	    public long calculateRemainingTime(FeedbackForm form) {
	        return form.getClosingDate() != null 
	            ? java.time.Duration.between(
	                LocalDateTime.now(),
	                form.getClosingDate()
	            ).toHours() 
	            : 0;
	    }
	
	    public List<Notification> getAllNotifications() {
	        return notificationRepository.findAll();
	    }
	
	    public List<Notification> getActiveNotifications() {
	        LocalDateTime now = LocalDateTime.now();
	        List<Notification> allNotifications = notificationRepository.findAll();
	        
	        List<Notification> activeNotifications = allNotifications.stream()
	            .filter(notification -> 
	                notification.getClosingDate() == null || 
	                notification.getClosingDate().isAfter(now))
	            .collect(Collectors.toList());
	        System.out.println("Total Notifications: " + allNotifications.size());
	        System.out.println("Active Notifications: " + activeNotifications.size());
	        
	        return activeNotifications;
	    }
	}