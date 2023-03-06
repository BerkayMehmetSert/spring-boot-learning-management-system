package com.bms.learningmanagementsystem.controller;

import com.bms.learningmanagementsystem.dto.EnrollmentDto;
import com.bms.learningmanagementsystem.service.EnrollmentService;
import com.bms.learningmanagementsystem.service.request.CreateEnrollmentRequest;
import com.bms.learningmanagementsystem.service.request.UpdateEnrollmentRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {
    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createEnrollment(@RequestBody CreateEnrollmentRequest request) {
        service.createEnrollment(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEnrollment(@PathVariable String id,
                                                 @RequestBody UpdateEnrollmentRequest request) {
        service.updateEnrollment(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelEnrollment(@PathVariable String id, @RequestParam String cancellationReason) {
        service.cancelEnrollment(id, cancellationReason);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable String id) {
        service.deleteEnrollment(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDto> getEnrollmentById(@PathVariable String id) {
        return ResponseEntity.ok(service.getEnrollmentById(id));
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentDto>> getAllEnrollments() {
        return ResponseEntity.ok(service.getAllEnrollments());
    }
}
