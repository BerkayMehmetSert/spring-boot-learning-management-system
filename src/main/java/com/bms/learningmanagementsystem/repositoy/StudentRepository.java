package com.bms.learningmanagementsystem.repositoy;

import com.bms.learningmanagementsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    boolean existsByNameIgnoreCase(String name);

    boolean existsByEmailIgnoreCase(String email);

    Optional<Student> findByStudentNo(String studentNo);
}