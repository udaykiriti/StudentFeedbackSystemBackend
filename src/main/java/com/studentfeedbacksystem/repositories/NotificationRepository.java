package com.studentfeedbacksystem.repositories;

import com.studentfeedbacksystem.models.Notification;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
	 List<Notification> findByClosingDateAfterOrClosingDateIsNull(LocalDateTime now);
}