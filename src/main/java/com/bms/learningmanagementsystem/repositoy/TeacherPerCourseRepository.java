package com.bms.learningmanagementsystem.repositoy;

import com.bms.learningmanagementsystem.model.TeacherPerCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherPerCourseRepository extends JpaRepository<TeacherPerCourse, String> {
}