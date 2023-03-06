package com.bms.learningmanagementsystem.repositoy;

import com.bms.learningmanagementsystem.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {
}