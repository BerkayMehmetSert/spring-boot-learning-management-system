package com.bms.learningmanagementsystem.controller;

import com.bms.learningmanagementsystem.dto.AttendanceDto;
import com.bms.learningmanagementsystem.service.AttendanceService;
import com.bms.learningmanagementsystem.service.request.CreateAttendanceRequest;
import com.bms.learningmanagementsystem.service.request.UpdateAttendanceRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attendances")
public class AttendanceController {
    private final AttendanceService service;

    public AttendanceController(AttendanceService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createAttendance(@RequestBody CreateAttendanceRequest request) {
        service.createAttendance(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAttendance(@PathVariable String id, @RequestBody UpdateAttendanceRequest request) {
        service.updateAttendance(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable String id) {
        service.deleteAttendance(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDto> getAttendanceById(@PathVariable String id) {
        return ResponseEntity.ok(service.getAttendanceById(id));
    }

    @GetMapping
    public ResponseEntity<List<AttendanceDto>> getAllAttendances() {
        return ResponseEntity.ok(service.getAllAttendances());
    }
}
