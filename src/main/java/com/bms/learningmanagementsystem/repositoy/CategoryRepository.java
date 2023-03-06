package com.bms.learningmanagementsystem.repositoy;

import com.bms.learningmanagementsystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByCategoryNo(String categoryNo);
    boolean existsByDescriptionIgnoreCase(String description);
}