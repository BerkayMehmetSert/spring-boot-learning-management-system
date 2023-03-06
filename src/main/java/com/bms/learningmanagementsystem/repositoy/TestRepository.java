package com.bms.learningmanagementsystem.repositoy;

import com.bms.learningmanagementsystem.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, String> {
}