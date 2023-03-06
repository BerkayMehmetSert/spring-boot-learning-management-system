package com.bms.learningmanagementsystem.controller;

import com.bms.learningmanagementsystem.dto.TeacherDto;
import com.bms.learningmanagementsystem.service.TeacherService;
import com.bms.learningmanagementsystem.service.request.CreateTeacherRequest;
import com.bms.learningmanagementsystem.service.request.UpdateTeacherRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {
    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createTeacher(@RequestBody CreateTeacherRequest request) {
        service.createTeacher(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTeacher(@PathVariable String id, @RequestBody UpdateTeacherRequest request) {
        service.updateTeacher(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable String id) {
        service.deleteTeacher(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable String id) {
        return ResponseEntity.ok(service.getTeacherById(id));
    }

    @GetMapping("/{teacherNo}")
    public ResponseEntity<TeacherDto> getTeacherByTeacherNo(@PathVariable String teacherNo) {
        return ResponseEntity.ok(service.getTeacherByTeacherNo(teacherNo));
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        return ResponseEntity.ok(service.getAllTeachers());
    }
}
