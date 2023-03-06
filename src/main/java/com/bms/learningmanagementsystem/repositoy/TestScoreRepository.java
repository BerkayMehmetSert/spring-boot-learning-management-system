package com.bms.learningmanagementsystem.repositoy;

import com.bms.learningmanagementsystem.model.TestScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestScoreRepository extends JpaRepository<TestScore, String> {
}