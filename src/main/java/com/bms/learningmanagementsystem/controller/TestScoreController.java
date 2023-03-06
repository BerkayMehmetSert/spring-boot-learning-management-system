package com.bms.learningmanagementsystem.controller;

import com.bms.learningmanagementsystem.dto.TestScoreDto;
import com.bms.learningmanagementsystem.service.TestScoreService;
import com.bms.learningmanagementsystem.service.request.CreateTestScoreRequest;
import com.bms.learningmanagementsystem.service.request.UpdateTestScoreRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/test-scores")
public class TestScoreController {
    private final TestScoreService service;

    public TestScoreController(TestScoreService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createTestScore(@RequestBody CreateTestScoreRequest request) {
        service.createTestScore(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTestScore(@PathVariable String id,
                                                @RequestBody UpdateTestScoreRequest request) {
        service.updateTestScore(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestScore(@PathVariable String id) {
        service.deleteTestScore(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestScoreDto> getTestScoreById(@PathVariable String id) {
        return ResponseEntity.ok(service.getTestScoreById(id));
    }

    @GetMapping
    public ResponseEntity<List<TestScoreDto>> getAllTestScores() {
        return ResponseEntity.ok(service.getAllTestScores());
    }
}
