package com.bms.learningmanagementsystem.controller;

import com.bms.learningmanagementsystem.dto.StudentDto;
import com.bms.learningmanagementsystem.service.StudentService;
import com.bms.learningmanagementsystem.service.request.CreateStudentRequest;
import com.bms.learningmanagementsystem.service.request.UpdateStudentRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createStudent(@RequestBody CreateStudentRequest request) {
        service.createStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStudent(@PathVariable String id, @RequestBody UpdateStudentRequest request) {
        service.updateStudent(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        service.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable String id) {
        return ResponseEntity.ok(service.getStudentById(id));
    }

    @GetMapping("/{studentNo}")
    public ResponseEntity<StudentDto> getStudentByStudentNo(@PathVariable String studentNo) {
        return ResponseEntity.ok(service.getStudentByStudentNo(studentNo));
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(service.getAllStudents());
    }
}
