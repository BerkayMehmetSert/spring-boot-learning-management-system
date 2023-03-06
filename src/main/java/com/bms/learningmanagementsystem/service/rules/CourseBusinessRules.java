package com.bms.learningmanagementsystem.service.rules;

import com.bms.learningmanagementsystem.core.exceptions.BusinessException;
import com.bms.learningmanagementsystem.core.exceptions.NotFoundException;
import com.bms.learningmanagementsystem.model.Course;
import com.bms.learningmanagementsystem.repositoy.CourseRepository;
import com.bms.learningmanagementsystem.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseBusinessRules {
    private final CourseRepository courseRepository;

    public CourseBusinessRules(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void checkIfCourseExists(String description) {
        var course = courseRepository.existsByDescriptionIgnoreCase(description);

        if (course) throw new BusinessException(BusinessMessages.COURSE_ALREADY_EXISTS);
    }

    public Course checkIfCourseExistsById(String id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessMessages.COURSE_NOT_FOUND));
    }

    public Course checkIfCourseExistsByCourseNo(String courseNo) {
        return courseRepository.findByCourseNo(courseNo)
                .orElseThrow(() -> new NotFoundException(BusinessMessages.COURSE_NOT_FOUND));
    }

    public void checkIfCourseListIsEmpty(List<Course> courses) {
        if (courses.isEmpty()) throw new NotFoundException(BusinessMessages.COURSE_LIST_IS_EMPTY);
    }
}
