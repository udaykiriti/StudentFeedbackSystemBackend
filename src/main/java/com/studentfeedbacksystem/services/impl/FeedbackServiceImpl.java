package com.studentfeedbacksystem.services.impl;

import com.studentfeedbacksystem.dto.CourseFeedbackDTO;
import com.studentfeedbacksystem.models.Course;
import com.studentfeedbacksystem.models.CourseFeedback;
import com.studentfeedbacksystem.repositories.CourseRepository;
import com.studentfeedbacksystem.repositories.CourseFeedbackRepository;
import com.studentfeedbacksystem.services.CourseFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeedbackServiceImpl implements CourseFeedbackService {

    @Autowired
    private CourseFeedbackRepository feedbackRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    @Transactional
    public CourseFeedback submitFeedback(CourseFeedbackDTO feedbackDTO) throws Exception {
        // Find the course
        Course course = courseRepository.findById(feedbackDTO.getCourseId())
            .orElseThrow(() -> new Exception("Course not found"));

        // Create feedback entity
        CourseFeedback feedback = new CourseFeedback(
            course,
            feedbackDTO.getRating(),
            feedbackDTO.getComment()
        );

        // Save feedback
        return feedbackRepository.save(feedback);
    }
}