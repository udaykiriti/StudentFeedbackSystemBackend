package com.studentfeedbacksystem.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentfeedbacksystem.models.GeneralFeedbackForm;
import com.studentfeedbacksystem.services.GeneralFeedbackService;

import io.micrometer.common.util.StringUtils;

@RestController
@RequestMapping("/api/hostel")
@CrossOrigin(origins = "http://localhost:3000")
public class HostelDataController {

    @Autowired
    private GeneralFeedbackService feedbackService;

    @PostMapping("/add")
    public ResponseEntity<GeneralFeedbackForm> addHostelData(@RequestBody GeneralFeedbackForm hostelData) {
        // Explicitly set isHosteler to true for hostel data
        hostelData.setHosteler(true);
        
        // Set submission date if not already set
        if (hostelData.getSubmissionDate() == null) {
            hostelData.setSubmissionDate(new Date());
        }

        // Validate required hostel-specific fields
        if (StringUtils.isEmpty(hostelData.getStudentId())) {
            return ResponseEntity.badRequest().body(null);
        }

        // Save the hostel-specific feedback
        GeneralFeedbackForm savedHostelData = feedbackService.submitFeedback(hostelData);
        return ResponseEntity.ok(savedHostelData);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GeneralFeedbackForm>> getAllHostelData() {
        // Fetch only hostel data
        List<GeneralFeedbackForm> hostelData = feedbackService.getFeedbackByHosteler(true);
        return ResponseEntity.ok(hostelData);
    }
}