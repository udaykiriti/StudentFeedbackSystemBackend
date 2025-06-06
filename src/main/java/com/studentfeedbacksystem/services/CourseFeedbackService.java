package com.studentfeedbacksystem.services;

import com.studentfeedbacksystem.dto.CourseFeedbackDTO;
import com.studentfeedbacksystem.models.CourseFeedback;

public interface CourseFeedbackService {
    CourseFeedback submitFeedback(CourseFeedbackDTO feedbackDTO) throws Exception;
}