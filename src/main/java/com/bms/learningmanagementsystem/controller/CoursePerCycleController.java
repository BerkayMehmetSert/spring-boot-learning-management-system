package com.bms.learningmanagementsystem.controller;

import com.bms.learningmanagementsystem.dto.CoursePerCycleDto;
import com.bms.learningmanagementsystem.service.CoursePerCycleService;
import com.bms.learningmanagementsystem.service.request.CreateCoursePerCycleRequest;
import com.bms.learningmanagementsystem.service.request.UpdateCoursePerCycleRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course-per-cycles")
public class CoursePerCycleController {
    private final CoursePerCycleService service;

    public CoursePerCycleController(CoursePerCycleService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createCoursePerCycle(@RequestBody CreateCoursePerCycleRequest request) {
        service.createCoursePerCycle(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCoursePerCycle(@PathVariable String id,
                                                     @RequestBody UpdateCoursePerCycleRequest request) {
        service.updateCoursePerCycle(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoursePerCycle(@PathVariable String id) {
        service.deleteCoursePerCycle(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoursePerCycleDto> getCoursePerCycleById(@PathVariable String id) {
        return ResponseEntity.ok(service.getCoursePerCycleById(id));
    }

    @GetMapping
    public ResponseEntity<List<CoursePerCycleDto>> getAllCoursePerCycles() {
        return ResponseEntity.ok(service.getAllCoursePerCycles());
    }
}
