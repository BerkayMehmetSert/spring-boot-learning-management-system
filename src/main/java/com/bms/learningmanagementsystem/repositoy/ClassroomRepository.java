package com.bms.learningmanagementsystem.repositoy;

import com.bms.learningmanagementsystem.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, String> {
    boolean existsByTitleIgnoreCase(String title);
}