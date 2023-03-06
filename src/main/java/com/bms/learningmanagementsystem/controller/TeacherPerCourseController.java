package com.bms.learningmanagementsystem.controller;

import com.bms.learningmanagementsystem.dto.TeacherPerCourseDto;
import com.bms.learningmanagementsystem.service.TeacherPerCourseService;
import com.bms.learningmanagementsystem.service.request.CreateTeacherPerCourseRequest;
import com.bms.learningmanagementsystem.service.request.UpdateTeacherPerCourseRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher-per-courses")
public class TeacherPerCourseController {
    private final TeacherPerCourseService service;

    public TeacherPerCourseController(TeacherPerCourseService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createTeacherPerCourse(@RequestBody CreateTeacherPerCourseRequest request) {
        service.createTeacherPerCourse(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTeacherPerCourse(@PathVariable String id,
                                                       @RequestBody UpdateTeacherPerCourseRequest request) {
        service.updateTeacherPerCourse(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacherPerCourse(@PathVariable String id) {
        service.deleteTeacherPerCourse(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherPerCourseDto> getTeacherPerCourseById(@PathVariable String id) {
        return ResponseEntity.ok(service.getTeacherPerCourseById(id));
    }

    @GetMapping
    public ResponseEntity<List<TeacherPerCourseDto>> getAllTeacherPerCourses() {
        return ResponseEntity.ok(service.getAllTeacherPerCourses());
    }
}
