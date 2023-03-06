package com.bms.learningmanagementsystem.controller;

import com.bms.learningmanagementsystem.dto.ClassroomDto;
import com.bms.learningmanagementsystem.service.ClassroomService;
import com.bms.learningmanagementsystem.service.request.CreateClassroomRequest;
import com.bms.learningmanagementsystem.service.request.UpdateClassroomRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classrooms")
public class ClassroomController {
    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createClassroom(@RequestBody CreateClassroomRequest request) {
        classroomService.createClassroom(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateClassroom(@PathVariable String id,
                                                @RequestBody UpdateClassroomRequest request) {
        classroomService.updateClassroom(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable String id) {
        classroomService.deleteClassroom(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomDto> getClassroomById(@PathVariable String id) {
        return ResponseEntity.ok(classroomService.getClassroomById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClassroomDto>> getAllClassrooms() {
        return ResponseEntity.ok(classroomService.getAllClassrooms());
    }
}
