package com.studentfeedbacksystem.services;

import com.studentfeedbacksystem.models.FeedbackForm;
import com.studentfeedbacksystem.models.FeedbackSubmission;
import com.studentfeedbacksystem.repositories.FeedbackFormRepository;
import com.studentfeedbacksystem.repositories.FeedbackSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackFormRepository formRepository;

    @Autowired
    private FeedbackSubmissionRepository submissionRepository;

    public List<FeedbackForm> getAllForms() {
        return formRepository.findAll();
    }

    public FeedbackForm createForm(FeedbackForm form) {
        return formRepository.save(form);
    }

    public void deleteForm(int formId) {
        formRepository.deleteById(formId);
    }

    public FeedbackSubmission submitFeedback(int formId, FeedbackSubmission submission) {
        submission.setFormId(formId);
        return submissionRepository.save(submission);
    }
}
