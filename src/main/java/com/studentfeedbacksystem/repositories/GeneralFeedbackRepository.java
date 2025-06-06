package com.studentfeedbacksystem.repositories;

import com.studentfeedbacksystem.models.GeneralFeedbackForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneralFeedbackRepository extends JpaRepository<GeneralFeedbackForm, Long> {
    List<GeneralFeedbackForm> findByDepartment(String department);
    List<GeneralFeedbackForm> findByIsHosteler(boolean isHosteler);
    List<GeneralFeedbackForm> findByStudentId(String studentId);
}
