package com.bms.learningmanagementsystem.controller;

import com.bms.learningmanagementsystem.dto.CategoryDto;
import com.bms.learningmanagementsystem.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createCategory(@RequestParam String description) {
        service.createCategory(description);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable String id, @RequestParam String description) {
        service.updateCategory(id, description);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        service.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable String id) {
        return ResponseEntity.ok(service.getCategoryById(id));
    }

    @GetMapping("/{categoryNo}")
    public ResponseEntity<CategoryDto> getCategoryByCategoryNo(@PathVariable String categoryNo) {
        return ResponseEntity.ok(service.getCategoryByCategoryNo(categoryNo));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(service.getAllCategories());
    }
}
