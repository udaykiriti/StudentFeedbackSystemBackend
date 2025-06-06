package com.studentfeedbacksystem.services;

import com.studentfeedbacksystem.dto.InstructorFeedbackDTO;
import com.studentfeedbacksystem.models.InstructorFeedback;

public interface InstructorFeedbackService {
    InstructorFeedback submitInstructorFeedback(InstructorFeedbackDTO instructorFeedbackDTO) throws Exception;
}