package com.bms.learningmanagementsystem.repositoy;

import com.bms.learningmanagementsystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, String> {
    Optional<Course> findByCourseNo(String courseNo);
    boolean existsByDescriptionIgnoreCase(String description);
}