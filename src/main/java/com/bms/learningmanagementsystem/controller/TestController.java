package com.bms.learningmanagementsystem.controller;

import com.bms.learningmanagementsystem.dto.TestDto;
import com.bms.learningmanagementsystem.service.TestService;
import com.bms.learningmanagementsystem.service.request.CreateTestRequest;
import com.bms.learningmanagementsystem.service.request.UpdateTestRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tests")
public class TestController {
    private final TestService service;

    public TestController(TestService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createTest(@RequestBody CreateTestRequest request) {
        service.createTest(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTest(@PathVariable String id, @RequestBody UpdateTestRequest request) {
        service.updateTest(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable String id) {
        service.deleteTest(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestDto> getTestById(@PathVariable String id) {
        return ResponseEntity.ok(service.getTestById(id));
    }

    @GetMapping
    public ResponseEntity<List<TestDto>> getAllTests() {
        return ResponseEntity.ok(service.getAllTests());
    }
}
