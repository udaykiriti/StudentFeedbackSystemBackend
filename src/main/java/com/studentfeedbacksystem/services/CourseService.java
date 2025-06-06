package com.studentfeedbacksystem.services;

import com.studentfeedbacksystem.dto.CourseDTO;
import com.studentfeedbacksystem.models.Course;
import java.util.List;

public interface CourseService {
    Course createCourse(CourseDTO courseDTO) throws Exception;
    
    // New method to get all courses
    List<Course> getAllCourses();
}