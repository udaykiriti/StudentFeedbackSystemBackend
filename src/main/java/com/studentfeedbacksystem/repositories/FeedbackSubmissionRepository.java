package com.studentfeedbacksystem.repositories;

import com.studentfeedbacksystem.models.FeedbackSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackSubmissionRepository extends JpaRepository<FeedbackSubmission, Integer> {
    List<FeedbackSubmission> findByFormId(int formId);
}
