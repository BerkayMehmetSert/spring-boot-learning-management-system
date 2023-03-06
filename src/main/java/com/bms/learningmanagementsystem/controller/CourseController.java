package com.bms.learningmanagementsystem.controller;

import com.bms.learningmanagementsystem.dto.CourseDto;
import com.bms.learningmanagementsystem.service.CourseService;
import com.bms.learningmanagementsystem.service.request.CreateCourseRequest;
import com.bms.learningmanagementsystem.service.request.UpdateCourseRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createCourse(@RequestBody CreateCourseRequest request) {
        service.createCourse(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCourse(@PathVariable String id, @RequestBody UpdateCourseRequest request) {
        service.updateCourse(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String id) {
        service.deleteCourse(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable String id) {
        return ResponseEntity.ok(service.getCourseById(id));
    }

    @GetMapping("{courseNo}")
    public ResponseEntity<CourseDto> getCourseByCourseNo(@PathVariable String courseNo) {
        return ResponseEntity.ok(service.getCourseByCourseNo(courseNo));
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        return ResponseEntity.ok(service.getAllCourses());
    }
}
