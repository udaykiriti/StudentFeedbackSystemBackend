package com.studentfeedbacksystem.services;

import com.studentfeedbacksystem.models.GeneralFeedbackForm;
import com.studentfeedbacksystem.repositories.GeneralFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminDashboardService {

    @Autowired
    private GeneralFeedbackRepository feedbackRepository;

    public Map<String, Object> getDashboardStats() {
        List<GeneralFeedbackForm> allFeedback = feedbackRepository.findAll();
        Map<String, Object> stats = new HashMap<>();

        // Total responses
        stats.put("totalResponses", allFeedback.size());

        // Department-wise responses
        Map<String, Long> departmentStats = allFeedback.stream()
                .collect(Collectors.groupingBy(GeneralFeedbackForm::getDepartment, Collectors.counting()));
        stats.put("departmentStats", departmentStats);

        // Hosteler vs Day Scholar ratio
        long hostelerCount = allFeedback.stream().filter(GeneralFeedbackForm::isHosteler).count();
        stats.put("hostelerCount", hostelerCount);
        stats.put("dayScholarCount", allFeedback.size() - hostelerCount);

        // Facility ratings analysis
        Map<String, Map<String, Long>> facilityRatings = new HashMap<>();
        
        // Building Cleanliness ratings
        facilityRatings.put("buildingCleanliness", getRatingDistribution(allFeedback, GeneralFeedbackForm::getBuildingCleanliness));
        
        // Classroom Condition ratings
        facilityRatings.put("classroomCondition", getRatingDistribution(allFeedback, GeneralFeedbackForm::getClassroomCondition));
        
        // Canteen ratings
        facilityRatings.put("foodQuality", getRatingDistribution(allFeedback, GeneralFeedbackForm::getFoodQuality));
        
        stats.put("facilityRatings", facilityRatings);

        // Recent feedback entries
        List<GeneralFeedbackForm> recentFeedback = allFeedback.stream()
                .sorted(Comparator.comparing(GeneralFeedbackForm::getSubmissionDate).reversed())
                .limit(10)
                .collect(Collectors.toList());
        stats.put("recentFeedback", recentFeedback);

        return stats;
    }

    private Map<String, Long> getRatingDistribution(List<GeneralFeedbackForm> feedback, 
            java.util.function.Function<GeneralFeedbackForm, String> ratingExtractor) {
        return feedback.stream()
                .map(ratingExtractor)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                    rating -> rating,
                    Collectors.counting()
                ));
    }

    public Map<String, Object> getHostelAnalytics() {
        List<GeneralFeedbackForm> hostelFeedback = feedbackRepository.findByIsHosteler(true);
        Map<String, Object> analytics = new HashMap<>();

        // Hostel ratings distribution
        analytics.put("roomCondition", getRatingDistribution(hostelFeedback, GeneralFeedbackForm::getRoomCondition));
        analytics.put("hostelFood", getRatingDistribution(hostelFeedback, GeneralFeedbackForm::getHostelFood));
        analytics.put("hostelCleanliness", getRatingDistribution(hostelFeedback, GeneralFeedbackForm::getHostelCleanliness));
        analytics.put("hostelStaff", getRatingDistribution(hostelFeedback, GeneralFeedbackForm::getHostelStaff));

        return analytics;
    }

    public Map<String, Object> getCanteenAnalytics() {
        List<GeneralFeedbackForm> allFeedback = feedbackRepository.findAll();
        Map<String, Object> analytics = new HashMap<>();

        analytics.put("canteenCleanliness", getRatingDistribution(allFeedback, GeneralFeedbackForm::getCanteenCleanliness));
        analytics.put("foodQuality", getRatingDistribution(allFeedback, GeneralFeedbackForm::getFoodQuality));
        analytics.put("canteenPricing", getRatingDistribution(allFeedback, GeneralFeedbackForm::getCanteenPricing));
        analytics.put("canteenVariety", getRatingDistribution(allFeedback, GeneralFeedbackForm::getCanteenVariety));

        return analytics;
    }
}