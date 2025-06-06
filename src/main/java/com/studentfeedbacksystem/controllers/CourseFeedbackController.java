package com.studentfeedbacksystem.controllers;

import com.studentfeedbacksystem.dto.CourseFeedbackDTO;
import com.studentfeedbacksystem.models.CourseFeedback;
import com.studentfeedbacksystem.services.CourseFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/feedback")
public class CourseFeedbackController {

    @Autowired
    private CourseFeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<?> submitFeedback(@jakarta.validation.Valid @RequestBody CourseFeedbackDTO feedbackDTO) {
        try {
            CourseFeedback submittedFeedback = feedbackService.submitFeedback(feedbackDTO);
            return new ResponseEntity<>(submittedFeedback, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}