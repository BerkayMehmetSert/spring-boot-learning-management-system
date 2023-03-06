package com.bms.learningmanagementsystem.repositoy;

import com.bms.learningmanagementsystem.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
    boolean existsByNameIgnoreCase(String name);

    boolean existsByEmail(String email);

    Optional<Teacher> findByTeacherNo(String teacherNo);
}