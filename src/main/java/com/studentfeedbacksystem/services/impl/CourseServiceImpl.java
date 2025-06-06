package com.studentfeedbacksystem.services.impl;

import com.studentfeedbacksystem.dto.CourseDTO;
import com.studentfeedbacksystem.models.Course;
import com.studentfeedbacksystem.repositories.CourseRepository;
import com.studentfeedbacksystem.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    @Transactional
    public Course createCourse(CourseDTO courseDTO) throws Exception {
        // Check if course code already exists
        if (courseRepository.existsByCourseCode(courseDTO.getCourseCode())) {
            throw new Exception("Course with this course code already exists");
        }

        // Convert DTO to Entity
        Course course = new Course(
                courseDTO.getCourseName(),
                courseDTO.getCourseCode(),
                courseDTO.getDescription(),
                courseDTO.getDepartment(),
                courseDTO.getCredits(),
                courseDTO.getInstructor(),
                courseDTO.getAcademicYear()  // Add academic year
            );
        // Save and return the course
        return courseRepository.save(course);
    }

    // New method to get all courses
    @Override
    @Transactional(readOnly = true)
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}