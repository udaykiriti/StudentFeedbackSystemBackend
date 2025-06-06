package com.studentfeedbacksystem.repositories;

import com.studentfeedbacksystem.models.FeedbackForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackFormRepository extends JpaRepository<FeedbackForm, Integer> {
}
