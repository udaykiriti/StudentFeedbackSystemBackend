package com.studentfeedbacksystem.services.impl;

import com.studentfeedbacksystem.models.Course;
import com.studentfeedbacksystem.models.CourseFeedback;
import com.studentfeedbacksystem.repositories.CourseRepository;
import com.studentfeedbacksystem.repositories.CourseFeedbackRepository;
import com.studentfeedbacksystem.services.FeedbackAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FeedbackAnalyticsServiceImpl implements FeedbackAnalyticsService {

    @Autowired
    private CourseFeedbackRepository feedbackRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Map<String, Object> generateFeedbackAnalytics() {
        Map<String, Object> analytics = new HashMap<>();

        // Get all feedbacks
        List<CourseFeedback> allFeedbacks = feedbackRepository.findAll();

        // Total number of feedbacks
        analytics.put("totalFeedbacks", allFeedbacks.size());

        // Overall average rating
        double averageRating = allFeedbacks.stream()
            .mapToInt(CourseFeedback::getRating)
            .average()
            .orElse(0.0);
        analytics.put("averageRating", averageRating);

        // Rating distribution
        Map<Integer, Long> ratingDistribution = allFeedbacks.stream()
            .collect(Collectors.groupingBy(
                CourseFeedback::getRating, 
                Collectors.counting()
            ));
        analytics.put("ratingDistribution", ratingDistribution);

        // Course-wise feedback analytics
        List<Course> courses = courseRepository.findAll();
        List<Map<String, Object>> courseFeedbacks = courses.stream()
            .map(course -> {
                List<CourseFeedback> courseFeedbackList = allFeedbacks.stream()
                    .filter(f -> f.getCourse().getId().equals(course.getId()))
                    .collect(Collectors.toList());

                Map<String, Object> courseFeedbackMap = new HashMap<>();
                courseFeedbackMap.put("courseName", course.getCourseName());
                courseFeedbackMap.put("totalFeedbacks", courseFeedbackList.size());
                courseFeedbackMap.put("averageRating", courseFeedbackList.stream()
                    .mapToInt(CourseFeedback::getRating)
                    .average()
                    .orElse(0.0));

                return courseFeedbackMap;
            })
            .collect(Collectors.toList());

        analytics.put("courseFeedbacks", courseFeedbacks);

        return analytics;
    }
}