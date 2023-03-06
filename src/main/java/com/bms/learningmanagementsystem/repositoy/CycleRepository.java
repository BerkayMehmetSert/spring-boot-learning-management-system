package com.bms.learningmanagementsystem.repositoy;

import com.bms.learningmanagementsystem.model.Cycle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CycleRepository extends JpaRepository<Cycle, String> {
    boolean existsByDescription(String description);
}