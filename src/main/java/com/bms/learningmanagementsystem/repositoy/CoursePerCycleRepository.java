package com.bms.learningmanagementsystem.repositoy;

import com.bms.learningmanagementsystem.model.CoursePerCycle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursePerCycleRepository extends JpaRepository<CoursePerCycle, String> {
}