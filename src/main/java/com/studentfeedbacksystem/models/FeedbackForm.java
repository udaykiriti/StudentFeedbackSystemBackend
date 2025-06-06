package com.studentfeedbacksystem.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class FeedbackForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Enumerated(EnumType.STRING)
    private FeedbackFormType title;

    private String customTitle;
    private String description;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "closing_date")
    private LocalDateTime closingDate;

    private boolean isUrgent = false;

    // Enum for predefined titles
    public enum FeedbackFormType {
        COURSE_FEEDBACK,
        EVENT_FEEDBACK,
        INSTRUCTOR_FEEDBACK,
        CAMPUS_SERVICES,
        CUSTOM
    }

    // Getters and setters
    public FeedbackFormType getTitle() {
        return title;
    }

    public void setTitle(FeedbackFormType title) {
        this.title = title;
    }

    public String getCustomTitle() {
        return customTitle;
    }

    public void setCustomTitle(String customTitle) {
        this.customTitle = customTitle;
    }

    public LocalDateTime getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDateTime closingDate) {
        this.closingDate = closingDate;
    }

    public boolean isUrgent() {
        return isUrgent;
    }

    public void setUrgent(boolean urgent) {
        isUrgent = urgent;
    }
}