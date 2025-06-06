package com.studentfeedbacksystem.repositories;
import com.studentfeedbacksystem.models.CourseFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseFeedbackRepository extends JpaRepository<CourseFeedback, Long> {
    // You can add custom query methods if needed
}
